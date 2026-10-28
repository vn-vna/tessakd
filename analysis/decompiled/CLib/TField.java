/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Command
 *  javax.microedition.lcdui.CommandListener
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.lcdui.TextBox
 *  javax.microedition.midlet.MIDlet
 */
package CLib;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import GameScreen.GameScreen;
import InterfaceComponents.ChatTextField;
import InterfaceComponents.MsgChat;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.T;
import com.silverknight.TemMidlet;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import javax.microedition.midlet.MIDlet;
import netcommand.global.GlobalService;

public class TField
extends AvMain {
    public static final byte KEY_LEFT = 14;
    public static final byte KEY_RIGHT = 15;
    public static final byte KEY_CLEAR = 19;
    public int x;
    public int y;
    public int width;
    public int widthTouch = 0;
    public int height;
    public boolean isFocus;
    public boolean lockArrow = false;
    public boolean paintFocus = true;
    public boolean isChangeFocus = true;
    public static int typeXpeed = 1;
    public static final int[] MAX_TIME_TO_CONFIRM_KEY = new int[]{18, 14, 11, 9, 6, 4, 2};
    public static int CARET_HEIGHT = 0;
    public static final int CARET_WIDTH = 1;
    public static final int CARET_SHOWING_TIME = 5;
    public static final int TEXT_GAP_X = 5;
    public static final int MAX_SHOW_CARET_COUNER = 10;
    public static final int INPUT_TYPE_ANY = 0;
    public static final int INPUT_TYPE_NUMERIC = 1;
    public static final int INPUT_TYPE_PASSWORD = 2;
    public static final int INPUT_ALPHA_NUMBER_ONLY = 3;
    public static String[] print = new String[]{" 0", ".,@?!_1\"/$-():*+<=>;%&~#%^&*{}[];'/1", "abc2\u00e2\u0103", "def3\u0111\u00ea", "ghi4", "jkl5", "mno6\u00f4\u01a1", "pqrs7", "tuv8\u01b0", "wxyz9", "*", "#"};
    private static String[] printA = new String[]{"0", "1", "abc2", "def3", "ghi4", "jkl5", "mno6", "pqrs7", "tuv8", "wxyz9", "0", "0"};
    private static String[] printBB = new String[]{" 0", "er1", "ty2", "ui3", "df4", "gh5", "jk6", "cv7", "bn8", "m9", "0", "0", "qw!", "as?", "zx", "op.", "l,"};
    private String text = "";
    private String passwordText = "";
    public String paintedText = "";
    public int caretPos = 0;
    public int counter = 0;
    private int maxTextLenght = 500;
    public int offsetX = 0;
    private static int lastKey = -1984;
    public int keyInActiveState = 0;
    private int indexOfActiveChar = 0;
    public int showCaretCounter = 10;
    private int inputType = 0;
    public static boolean isQwerty;
    public static int typingModeAreaWidth;
    public static final byte abc = 0;
    public static final byte Abc = 1;
    public static final byte ABC = 2;
    public static final byte number123 = 3;
    public static int mode;
    public static int timeChangeMode;
    public static final String[] modeNotify;
    public static final int NOKIA = 0;
    public static final int MOTO = 1;
    public static final int ORTHER = 2;
    public static final int BB = 3;
    public static int changeModeKey;
    public static int changeDau;
    public iCommand cmdClear;
    public String sDefaust = "";
    public String title = "";
    public String strnull = "";
    public static int xDu;
    public static int yDu;
    int xCamText = 0;
    public boolean isCloseKey = true;
    public static boolean isOpenTextBox;
    private int indexDau = -1;
    private int indexTemplate = 0;
    private int indexCong = 0;
    private long timeDau = 0L;
    private static String printDau;
    int timeFocus = 0;
    boolean openVirtual = false;
    public static int[][] BBKEY;

    public void commandPointer(int n, int n2) {
        switch (n) {
            case 0: {
                if (!this.isFocus) break;
                this.clear();
            }
        }
    }

    public static void setVendorTypeMode(int n) {
        if (n == 1) {
            TField.print[0] = "0";
            TField.print[10] = " *";
            TField.print[11] = "#";
            changeModeKey = 35;
            changeDau = 42;
        } else if (n == 0) {
            TField.print[0] = " 0";
            TField.print[10] = "*";
            TField.print[11] = "#";
            changeModeKey = 35;
            changeDau = 42;
        } else if (n == 2) {
            TField.print[0] = "0";
            TField.print[10] = "*";
            TField.print[11] = " #";
            changeModeKey = 42;
            changeDau = 35;
        }
    }

    public void setFocus(boolean bl) {
        if (this.isFocus != bl) {
            mode = 0;
        }
        lastKey = -1984;
        timeChangeMode = GameCanvas.getSecond();
        this.isFocus = bl;
    }

    public iCommand setCmdClear() {
        return this.cmdClear;
    }

    public static int getHeight() {
        if (GameCanvas.isTouch) {
            return 28;
        }
        return 20;
    }

    private void init() {
        CARET_HEIGHT = mFont.tahoma_7_black.getHeight() + 1;
        this.cmdClear = new iCommand(T.del, 0, this);
    }

    public static void setQwerty(boolean bl) {
        isQwerty = bl;
        typingModeAreaWidth = mFont.tahoma_7_black.getWidth("ABC") + 5;
        if (bl) {
            typingModeAreaWidth = 0;
        }
    }

    public TField() {
        this.init();
        this.setFocus(false);
        this.setheightText();
    }

    public TField(int n, int n2) {
        this.x = n;
        this.y = n2;
        this.init();
        this.setFocus(false);
        this.setheightText();
    }

    public TField(int n, int n2, int n3) {
        this.x = n;
        this.y = n2;
        this.width = n3;
        this.widthTouch = 0;
        this.init();
        this.setFocus(false);
        this.setheightText();
    }

    public TField(int n, int n2, int n3, int n4) {
        this.x = n;
        this.y = n2;
        this.width = n3;
        this.widthTouch = n4;
        this.init();
        this.setFocus(false);
        this.setheightText();
    }

    public void setheightText() {
        this.height = 20;
        if (GameCanvas.isTouch) {
            this.height = 28;
        }
    }

    public void setStringNull(String string) {
        this.strnull = string;
    }

    public void clear() {
        if (this.caretPos > 0 && this.text.length() > 0) {
            this.text = this.text.substring(0, this.caretPos - 1) + this.text.substring(this.caretPos, this.text.length());
            --this.caretPos;
            this.setOffset(0);
            this.setPasswordTest();
        }
    }

    public void setOffset(int n) {
        int n2;
        this.paintedText = this.inputType == 2 ? this.passwordText : this.text;
        int n3 = mFont.tahoma_7_black.getWidth(this.paintedText.substring(0, this.caretPos));
        if (n == -1) {
            if (n3 + this.offsetX < 15 && this.caretPos > 0 && this.caretPos < this.paintedText.length()) {
                this.offsetX += mFont.tahoma_7_black.getWidth(this.paintedText.substring(this.caretPos, this.caretPos + 1));
            }
        } else if (n == 1) {
            if (n3 + this.offsetX > this.width - 25 && this.caretPos < this.paintedText.length() && this.caretPos > 0) {
                this.offsetX -= mFont.tahoma_7_black.getWidth(this.paintedText.substring(this.caretPos - 1, this.caretPos));
            }
        } else {
            this.offsetX = -(n3 - (this.width - 12));
        }
        if (this.offsetX > 0) {
            this.offsetX = 0;
        } else if (this.offsetX < 0 && this.offsetX < -(n2 = mFont.tahoma_7_black.getWidth(this.paintedText) - (this.width - 12))) {
            this.offsetX = -n2;
        }
    }

    private void keyPressedAny(int n) {
        String[] stringArray = null;
        stringArray = GameCanvas.isBB ? printBB : (this.inputType == 2 || this.inputType == 3 ? printA : print);
        if (GameCanvas.isBB && (n = this.getBBKeyCode(n)) == -1) {
            return;
        }
        if (n == lastKey) {
            this.indexOfActiveChar = (this.indexOfActiveChar + 1) % stringArray[n - 48].length();
            char c = stringArray[n - 48].charAt(this.indexOfActiveChar);
            c = mode == 0 ? Character.toLowerCase(c) : (mode == 1 ? Character.toUpperCase(c) : (mode == 2 ? Character.toUpperCase(c) : stringArray[n - 48].charAt(stringArray[n - 48].length() - 1)));
            String string = this.text.substring(0, this.caretPos - 1) + c;
            if (this.caretPos < this.text.length()) {
                string = string + this.text.substring(this.caretPos, this.text.length());
            }
            this.text = string;
            this.keyInActiveState = MAX_TIME_TO_CONFIRM_KEY[typeXpeed];
            this.setPasswordTest();
        } else if (this.text.length() < this.maxTextLenght) {
            if (mode == 1 && lastKey != -1984) {
                mode = 0;
            }
            this.indexOfActiveChar = 0;
            char c = stringArray[n - 48].charAt(this.indexOfActiveChar);
            c = mode == 0 ? Character.toLowerCase(c) : (mode == 1 ? Character.toUpperCase(c) : (mode == 2 ? Character.toUpperCase(c) : stringArray[n - 48].charAt(stringArray[n - 48].length() - 1)));
            String string = this.text.substring(0, this.caretPos) + c;
            if (this.caretPos < this.text.length()) {
                string = string + this.text.substring(this.caretPos, this.text.length());
            }
            this.text = string;
            this.keyInActiveState = MAX_TIME_TO_CONFIRM_KEY[typeXpeed];
            ++this.caretPos;
            this.setPasswordTest();
            this.setOffset(0);
        }
        lastKey = n;
    }

    private void keyPressedAscii(int n) {
        if (!(this.inputType != 2 && this.inputType != 3 || n >= 48 && n <= 57 || n >= 65 && n <= 90 || n >= 97 && n <= 122)) {
            return;
        }
        if (this.text.length() < this.maxTextLenght) {
            String string = this.text.substring(0, this.caretPos) + (char)n;
            if (this.caretPos < this.text.length()) {
                string = string + this.text.substring(this.caretPos, this.text.length());
            }
            this.text = string;
            ++this.caretPos;
            this.setPasswordTest();
            this.setOffset(0);
        }
    }

    public static void setMode() {
        if (++mode > 3) {
            mode = 0;
        }
        lastKey = changeModeKey;
        timeChangeMode = GameCanvas.getSecond();
    }

    public void setTypeKey(int n) {
        if (this.inputType == 0 || this.inputType == 2 || this.inputType == 3) {
            this.keyPressedAny(n);
        } else if (this.inputType == 1) {
            this.keyPressedAscii(n);
            this.keyInActiveState = 1;
        }
    }

    public boolean keyPressed(int n) {
        if (GameCanvas.isBB) {
            if (n == 8 || n == 127) {
                this.clear();
            }
        } else if (n == 8 || n == -8 || n == 204) {
            this.clear();
            return true;
        }
        if (!GameCanvas.isTouch && !GameCanvas.isBB && n >= 65 && n <= 122) {
            isQwerty = true;
            typingModeAreaWidth = 0;
            byte[] byArray = new byte[]{1};
            try {
                CRes.saveRMS("isQty", byArray);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (isQwerty && !GameCanvas.isBB) {
            if (n == 45) {
                if (n == lastKey && this.keyInActiveState < MAX_TIME_TO_CONFIRM_KEY[typeXpeed]) {
                    this.paintedText = this.text = this.text.substring(0, this.caretPos - 1) + '_';
                    this.setPasswordTest();
                    this.setOffset(0);
                    lastKey = -1984;
                    return false;
                }
                lastKey = 45;
            }
            if (n >= 32) {
                this.keyPressedAscii(n);
                return false;
            }
        }
        if (!isQwerty && n == changeModeKey) {
            TField.setMode();
            this.keyInActiveState = 1;
            lastKey = n;
            return false;
        }
        if (n == changeDau && this.inputType == 0) {
            this.setDau();
            return false;
        }
        if (n == 42) {
            n = 58;
        }
        if (n == 35) {
            n = 59;
        }
        if (GameCanvas.isBB && n >= 48) {
            if (isQwerty) {
                this.keyPressedAscii(n);
                this.keyInActiveState = 1;
            } else {
                this.setTypeKey(n);
            }
        } else if (n >= 48 && n <= 59) {
            this.setTypeKey(n);
        } else {
            this.indexOfActiveChar = 0;
            lastKey = -1984;
            if (n == 14 && !this.lockArrow) {
                if (this.caretPos > 0) {
                    --this.caretPos;
                    this.setOffset(0);
                    this.showCaretCounter = 10;
                    return false;
                }
            } else if (n == 15 && !this.lockArrow) {
                if (this.caretPos < this.text.length()) {
                    ++this.caretPos;
                    this.setOffset(0);
                    this.showCaretCounter = 10;
                    return false;
                }
            } else {
                if (n == 19) {
                    this.clear();
                    return false;
                }
                lastKey = n;
            }
        }
        return true;
    }

    private void setDau() {
        this.timeDau = mSystem.currentTimeMillis() / 100L;
        if (this.indexDau == -1) {
            for (int i = this.caretPos; i > 0; --i) {
                char c = this.text.charAt(i - 1);
                for (int j = 0; j < printDau.length(); ++j) {
                    char c2 = printDau.charAt(j);
                    if (c != c2) continue;
                    this.indexTemplate = j;
                    this.indexCong = 0;
                    this.indexDau = i - 1;
                    return;
                }
            }
            this.indexDau = -1;
        } else {
            ++this.indexCong;
            if (this.indexCong >= 6) {
                this.indexCong = 0;
            }
            String string = this.text.substring(0, this.indexDau);
            String string2 = this.text.substring(this.indexDau + 1);
            String string3 = printDau.substring(this.indexTemplate + this.indexCong, this.indexTemplate + this.indexCong + 1);
            this.text = string + string3 + string2;
        }
    }

    public void paintByList(mGraphics mGraphics2) {
        int n;
        boolean bl = this.isFocused();
        mFont mFont2 = mFont.tahoma_7_black;
        int n2 = 0;
        if (this.inputType == 2) {
            this.paintedText = this.passwordText;
            n2 = 3;
        } else {
            this.paintedText = this.text;
        }
        int n3 = 0;
        mGraphics2.setColor(12621920);
        ++this.timeFocus;
        if (bl && (n = this.paintedText.length()) > 0 && this.caretPos > 0) {
            n3 = mFont.tahoma_7_black.getWidth(this.paintedText.substring(0, this.caretPos));
        }
        AvMain.paintRectText(mGraphics2, this.x, this.y, this.width, this.height + 2, bl);
        if (this.paintedText.length() == 0) {
            n2 = 0;
            this.paintedText = this.strnull;
            mFont2 = mFont.tahoma_7_white;
        }
        mFont2.drawString(mGraphics2, this.paintedText, this.x + 4, this.y + this.height / 2 - 5 + n2, 0, true);
        if (bl && this.timeFocus % 16 > 12) {
            mGraphics2.setColor(0);
            mGraphics2.fillRect(this.x + 3 + n3, this.y + this.height / 2 - 7, 1, 14, false);
        }
    }

    public void paint(mGraphics mGraphics2) {
        int n;
        boolean bl = this.isFocused();
        mFont mFont2 = mFont.tahoma_7_black;
        int n2 = 0;
        if (this.inputType == 2) {
            this.paintedText = this.passwordText;
            n2 = 3;
        } else {
            this.paintedText = this.text;
        }
        int n3 = 0;
        mGraphics2.setColor(12621920);
        ++this.timeFocus;
        if (bl && (n = this.paintedText.length()) > 0 && this.caretPos > 0) {
            n3 = mFont.tahoma_7_black.getWidth(this.paintedText.substring(0, this.caretPos));
        }
        AvMain.paintRectText(mGraphics2, this.x, this.y, this.width, this.height + 2, bl);
        mGraphics2.setClip(this.x + 2, this.y + 2, this.width - 4, this.height - 3);
        n = mGraphics2.getTranslateX();
        int n4 = mGraphics2.getTranslateY();
        mGraphics2.translate(-this.xCamText, 0);
        if (this.paintedText.length() == 0) {
            n2 = 0;
            this.paintedText = this.strnull;
            mFont2 = mFont.tahoma_7_white;
        }
        mFont2.drawString(mGraphics2, this.paintedText, this.x + 4, this.y + this.height / 2 - 5 + n2, 0, true);
        if (bl && this.timeFocus % 16 > 12) {
            mGraphics2.setColor(0);
            mGraphics2.fillRect(this.x + 3 + n3, this.y + this.height / 2 - 7, 1, 14, false);
        }
        mGraphics2.endClip();
        mGraphics2.setClip(0, 0, GameCanvas.w, GameCanvas.h);
        mGraphics2.endClip();
        GameCanvas.resetTrans(mGraphics2);
        mGraphics2.translate(n, n4);
    }

    public boolean isFocused() {
        return this.isFocus;
    }

    private void setPasswordTest() {
        if (this.inputType == 2) {
            this.passwordText = "";
            for (int i = 0; i < this.text.length(); ++i) {
                this.passwordText = this.passwordText + "*";
            }
            if (this.keyInActiveState > 0 && this.caretPos > 0) {
                this.passwordText = this.passwordText.substring(0, this.caretPos - 1) + this.text.charAt(this.caretPos - 1) + this.passwordText.substring(this.caretPos, this.passwordText.length());
            }
        }
    }

    public void updatePoiter() {
        this.setTextBox();
    }

    public void update() {
        if (this.isFocus) {
            String string = this.inputType == 2 ? this.passwordText : this.text;
            this.xCamText = -this.width / 2 + this.caretPos * 5 + 4;
            int n = mFont.tahoma_7_black.getWidth(string) - this.width + 8;
            if (this.xCamText > n) {
                this.xCamText = n;
            }
            if (this.xCamText < 0) {
                this.xCamText = 0;
            }
        } else {
            this.xCamText = 0;
        }
        ++this.counter;
        if (this.keyInActiveState > 0) {
            --this.keyInActiveState;
            if (this.keyInActiveState == 0 || mode > 2) {
                this.indexOfActiveChar = 0;
                if (this.isFocus && mode == 1 && lastKey != changeModeKey) {
                    mode = 0;
                }
                lastKey = -1984;
                this.setPasswordTest();
            }
        }
        if (this.showCaretCounter > 0) {
            --this.showCaretCounter;
        }
        if (this.indexDau != -1 && mSystem.currentTimeMillis() / 100L - this.timeDau > 5L) {
            this.indexDau = -1;
        }
        if (this.isFocus) {
            if (GameCanvas.keyMyPressed[4]) {
                --this.caretPos;
                if (this.caretPos < 0) {
                    this.caretPos = 0;
                }
                this.setOffset(-1);
                GameCanvas.keyMyPressed[4] = false;
            } else if (GameCanvas.keyMyPressed[6]) {
                ++this.caretPos;
                if (this.caretPos > this.getText().length()) {
                    this.caretPos = this.getText().length();
                }
                this.setOffset(1);
                GameCanvas.keyMyPressed[6] = false;
            }
        }
    }

    public void updatepointerByList() {
        if (!this.isFocus) {
            this.isFocus = true;
            if (!isOpenTextBox) {
                isOpenTextBox = true;
                GameCanvas.isPointerSelect = false;
                this.doChangeToTextBox();
                return;
            }
        } else if (!isOpenTextBox) {
            isOpenTextBox = true;
            GameCanvas.isPointerSelect = false;
            this.doChangeToTextBox();
            return;
        }
    }

    public void setTextBox() {
        if (GameCanvas.isPointerSelect && GameCanvas.isPoint(0, 0, GameCanvas.w, GameCanvas.h - GameCanvas.hCommand / 2)) {
            if (GameCanvas.isPoint(this.x, this.y - 6, this.width - this.widthTouch, this.height + 12)) {
                if (!this.isFocus) {
                    this.isFocus = true;
                    if (!isOpenTextBox) {
                        isOpenTextBox = true;
                        GameCanvas.isPointerSelect = false;
                        this.doChangeToTextBox();
                        return;
                    }
                } else if (!isOpenTextBox) {
                    isOpenTextBox = true;
                    GameCanvas.isPointerSelect = false;
                    this.doChangeToTextBox();
                    return;
                }
            } else if (this.isChangeFocus) {
                this.isFocus = false;
            }
        }
    }

    public void setPoiter() {
        this.isFocus = true;
        isOpenTextBox = true;
        GameCanvas.isPointerSelect = false;
        this.doChangeToTextBox();
    }

    public String getText() {
        return this.text;
    }

    public void setText(String string) {
        if (string == null) {
            return;
        }
        lastKey = -1984;
        this.keyInActiveState = 0;
        this.indexOfActiveChar = 0;
        this.text = string;
        this.paintedText = string;
        this.setPasswordTest();
        this.caretPos = string.length();
        this.setOffset(0);
    }

    public void setMaxTextLenght(int n) {
        this.maxTextLenght = n;
    }

    public void setIputType(int n) {
        this.inputType = n;
    }

    public int getBBKeyCode(int n) {
        for (int i = 0; i < BBKEY.length; ++i) {
            for (int j = 0; j < BBKEY[i].length; ++j) {
                if (BBKEY[i][j] != n) continue;
                return i + 48;
            }
        }
        return -1;
    }

    public void doChangeToTextBox() {
        final TextBox textBox = new TextBox(this.title, this.sDefaust, 200, 0);
        textBox.addCommand(new Command("OK", 4, 0));
        textBox.addCommand(new Command("Cancel", 3, 0));
        textBox.setCommandListener(new CommandListener(){

            public void commandAction(Command command, Displayable displayable) {
                isOpenTextBox = false;
                TField.this.isFocus = false;
                if (command.getLabel().equals("OK")) {
                    TField.this.setText(textBox.getString());
                    if (ChatTextField.isShow) {
                        if (ChatTextField.gI().tfChat.getText().length() > 0) {
                            GameScreen.player.strChatPopup = ChatTextField.gI().tfChat.getText();
                            GlobalService.gI().chatPopup(ChatTextField.gI().tfChat.getText());
                            ChatTextField.gI().tfChat.setText("");
                        }
                        ChatTextField.isShow = false;
                    } else if (GameCanvas.subDialog != null && GameCanvas.subDialog == GameCanvas.msgchat) {
                        MsgChat.cmdChat.perform();
                    }
                }
                if (command.getLabel().equals("Cancel") && ChatTextField.isShow) {
                    ChatTextField.isShow = false;
                }
                Display.getDisplay((MIDlet)TemMidlet.instance).setCurrent((Displayable)TemMidlet.temCanvas);
                TemMidlet.temCanvas.setFullScreenMode(true);
            }
        });
        try {
            if (this.inputType == 2) {
                textBox.setConstraints(65536);
            } else if (this.inputType == 1) {
                textBox.setConstraints(2);
            } else {
                textBox.setConstraints(0);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        textBox.setString(this.getText());
        textBox.setMaxSize(this.maxTextLenght);
        Display.getDisplay((MIDlet)TemMidlet.instance).setCurrent((Displayable)textBox);
    }

    static {
        mode = 0;
        modeNotify = new String[]{"abc", "Abc", "ABC", "123"};
        changeModeKey = 11;
        isOpenTextBox = false;
        printDau = "a\u00e1\u00e0\u1ea3\u00e3\u1ea1\u00e2\u1ea5\u1ea7\u1ea9\u1eab\u1ead\u0103\u1eaf\u1eb1\u1eb3\u1eb5\u1eb7e\u00e9\u00e8\u1ebb\u1ebd\u1eb9\u00ea\u1ebf\u1ec1\u1ec3\u1ec5\u1ec7i\u00ed\u00ec\u1ec9\u0129\u1ecbo\u00f3\u00f2\u1ecf\u00f5\u1ecd\u00f4\u1ed1\u1ed3\u1ed5\u1ed7\u1ed9\u01a1\u1edb\u1edd\u1edf\u1ee1\u1ee3u\u00fa\u00f9\u1ee7\u0169\u1ee5\u01b0\u1ee9\u1eeb\u1eed\u1eef\u1ef1y\u00fd\u1ef3\u1ef7\u1ef9\u1ef5";
        BBKEY = new int[][]{{32, 48}, {49, 69}, {50, 84}, {51, 85}, {52, 68}, {53, 71}, {54, 74}, {55, 67}, {56, 66}, {57, 77}, {42, 128}, {35, 137}, {33, 113}, {63, 97}, {64, 121, 122}, {46, 111}, {44, 108}};
    }
}

