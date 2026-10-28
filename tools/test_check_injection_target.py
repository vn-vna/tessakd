"""Focused tests for compatibility-inspection failure cases; no game execution."""

import io
import unittest
from unittest.mock import Mock, patch
import zipfile

from check_injection_target import inspect_archive, inspect_target, parse_manifest, parse_methods


class InjectionPreflightTests(unittest.TestCase):
    def test_method_matching_keeps_overloads_and_descriptors_distinct(self):
        methods = parse_methods("""
  public void update();
    descriptor: ()V
  public void update(int);
    descriptor: (I)V
  private int value;
    descriptor: I
""")
        self.assertEqual(methods, {("update", "()V"), ("update", "(I)V")})
        self.assertNotIn(("update", "(J)V"), methods)

    def test_manifest_continuations_and_main_section(self):
        attributes = parse_manifest(b"MIDlet-1: KnightOnline,/icon.png,com.silver\r\n knight.TemMidlet\r\n\r\nName: other\r\n")
        self.assertEqual(attributes["MIDlet-1"], "KnightOnline,/icon.png,com.silverknight.TemMidlet")
        self.assertNotIn("Name", attributes)

    def test_case_colliding_members_are_rejected(self):
        buffer = io.BytesIO()
        with zipfile.ZipFile(buffer, "w") as archive:
            archive.writestr("A.class", b"\xca\xfe\xba\xbe\x00\x03\x00\x2d")
            archive.writestr("a.class", b"\xca\xfe\xba\xbe\x00\x03\x00\x2d")
        with self.assertRaisesRegex(ValueError, "case-colliding"):
            inspect_archive(buffer.getvalue())

    def test_invalid_class_header_is_rejected(self):
        buffer = io.BytesIO()
        with zipfile.ZipFile(buffer, "w") as archive:
            archive.writestr("Example.class", b"not-java")
        with self.assertRaisesRegex(ValueError, "Invalid class header"):
            inspect_archive(buffer.getvalue())

    def test_unknown_hash_is_not_trusted_from_version_label(self):
        buffer = io.BytesIO()
        with zipfile.ZipFile(buffer, "w") as archive:
            archive.writestr("META-INF/MANIFEST.MF", "Manifest-Version: 1.0\nMIDlet-Version: 4.0.3\n\n")
        data = buffer.getvalue()
        path = Mock()
        path.stat.return_value.st_size = len(data)
        path.read_bytes.return_value = data
        with patch("check_injection_target.subprocess.run") as javap:
            result = inspect_target(path)
        javap.assert_not_called()
        self.assertEqual(result["status"], "unsupported-or-mismatching-input")
        self.assertFalse(result["structural_requirements_met"])


if __name__ == "__main__":
    unittest.main()
