"""Check documentation links and Java source line citations without running the game."""

from pathlib import Path
import re


ROOT = Path(__file__).resolve().parents[1]
SOURCE = ROOT / "analysis" / "decompiled"
DOCS = ROOT / "analysis" / "docs"
CITATION = re.compile(r"([A-Za-z_][\w/$]*(?:/[\w$]+)*\.java):(\d+)(?:-(\d+))?")
LINK = re.compile(r"\[[^\]]*\]\(([^)]+)\)")


def main():
    errors = []
    citations = 0
    links = 0
    files = sorted(DOCS.glob("*.md"))
    for document in files:
        text = document.read_text(encoding="utf-8")
        for match in CITATION.finditer(text):
            name, start, end = match.groups()
            path = SOURCE / name
            if not path.is_file():
                errors.append(f"{document.name}: missing source {name}")
                continue
            count = len(path.read_text(encoding="utf-8").splitlines())
            if not 1 <= int(start) <= int(end or start) <= count:
                errors.append(f"{document.name}: invalid range {match.group(0)} ({count} lines)")
            citations += 1
        for target in LINK.findall(text):
            if "://" in target or target.startswith("#"):
                continue
            path = (document.parent / target.split("#", 1)[0]).resolve()
            if not path.exists():
                errors.append(f"{document.name}: missing link {target}")
            links += 1
    if not files:
        errors.append("No documentation found")
    if errors:
        raise SystemExit("\n".join(errors))
    print(f"OK: {len(files)} documents, {citations} source citations, {links} local links")
    print("Checks path/range validity only; behavioral claims require source/bytecode review.")


if __name__ == "__main__":
    main()
