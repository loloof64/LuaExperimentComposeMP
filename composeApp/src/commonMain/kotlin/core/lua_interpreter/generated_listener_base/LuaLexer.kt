import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATN
import org.antlr.v4.runtime.atn.ATNDeserializer
import org.antlr.v4.runtime.atn.LexerATNSimulator
import org.antlr.v4.runtime.atn.PredictionContextCache
import org.antlr.v4.runtime.dfa.DFA

@Suppress("unused")
class LuaLexer(input: CharStream?) : LuaLexerBase(input) {
    @Deprecated("")
    override fun getTokenNames(): Array<String?> {
        return Companion.tokenNames
    }

    override fun getVocabulary(): Vocabulary {
        return VOCABULARY
    }


    override fun getGrammarFileName(): String {
        return "Lua.g4"
    }

    override fun getRuleNames(): Array<String> {
        return Companion.ruleNames
    }

    override fun getSerializedATN(): String {
        return _serializedATN
    }

    override fun getChannelNames(): Array<String> {
        return Companion.channelNames
    }

    override fun getModeNames(): Array<String> {
        return Companion.modeNames
    }

    override fun getATN(): ATN {
        return _ATN
    }

    override fun action(_localctx: RuleContext, ruleIndex: Int, actionIndex: Int) {
        when (ruleIndex) {
            38 -> COMMENT_action(_localctx, actionIndex)
        }
    }

    private fun COMMENT_action(_localctx: RuleContext, actionIndex: Int) {
        when (actionIndex) {
            0 -> this.HandleComment()
        }
    }

    init {
        _interp = LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache)
    }

    companion object {
        init {
            RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION)
        }

        protected val _decisionToDFA: Array<DFA?>
        protected val _sharedContextCache: PredictionContextCache = PredictionContextCache()
        const val SEMI: Int = 1
        const val EQ: Int = 2
        const val END: Int = 3
        const val IF: Int = 4
        const val THEN: Int = 5
        const val ELSEIF: Int = 6
        const val ELSE: Int = 7
        const val COMMA: Int = 8
        const val LT: Int = 9
        const val GT: Int = 10
        const val FALSE: Int = 11
        const val TRUE: Int = 12
        const val DOT: Int = 13
        const val SQUIG: Int = 14
        const val MINUS: Int = 15
        const val OP: Int = 16
        const val CP: Int = 17
        const val NOT: Int = 18
        const val LL: Int = 19
        const val GG: Int = 20
        const val AMP: Int = 21
        const val SS: Int = 22
        const val PER: Int = 23
        const val LE: Int = 24
        const val GE: Int = 25
        const val AND: Int = 26
        const val OR: Int = 27
        const val PLUS: Int = 28
        const val STAR: Int = 29
        const val EE: Int = 30
        const val PIPE: Int = 31
        const val CARET: Int = 32
        const val SLASH: Int = 33
        const val SQEQ: Int = 34
        const val NAME: Int = 35
        const val INT: Int = 36
        const val COMMENT: Int = 37
        const val WS: Int = 38
        const val NL: Int = 39
        var channelNames: Array<String> = arrayOf(
            "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
        )

        var modeNames: Array<String> = arrayOf(
            "DEFAULT_MODE"
        )

        private fun makeRuleNames(): Array<String> {
            return arrayOf(
                "SEMI", "EQ", "END", "IF", "THEN", "ELSEIF", "ELSE", "COMMA", "LT", "GT",
                "FALSE", "TRUE", "DOT", "SQUIG", "MINUS", "OP", "CP", "NOT", "LL", "GG",
                "AMP", "SS", "PER", "LE", "GE", "AND", "OR", "PLUS", "STAR", "EE", "PIPE",
                "CARET", "SLASH", "SQEQ", "NAME", "INT", "EscapeSequence", "Digit", "COMMENT",
                "WS", "NL"
            )
        }

        val ruleNames: Array<String> = makeRuleNames()

        private fun makeLiteralNames(): Array<String?> {
            return arrayOf(
                null, "';'", "'='", "'end'", "'if'", "'then'", "'elseif'", "'else'",
                "','", "'<'", "'>'", "'false'", "'true'", "'.'", "'~'", "'-'", "'('",
                "')'", "'not'", "'<<'", "'>>'", "'&'", "'//'", "'%'", "'<='", "'>='",
                "'and'", "'or'", "'+'", "'*'", "'=='", "'|'", "'^'", "'/'", "'~='"
            )
        }

        private val _LITERAL_NAMES = makeLiteralNames()
        private fun makeSymbolicNames(): Array<String?> {
            return arrayOf(
                null, "SEMI", "EQ", "END", "IF", "THEN", "ELSEIF", "ELSE", "COMMA", "LT",
                "GT", "FALSE", "TRUE", "DOT", "SQUIG", "MINUS", "OP", "CP", "NOT", "LL",
                "GG", "AMP", "SS", "PER", "LE", "GE", "AND", "OR", "PLUS", "STAR", "EE",
                "PIPE", "CARET", "SLASH", "SQEQ", "NAME", "INT", "COMMENT", "WS", "NL"
            )
        }

        private val _SYMBOLIC_NAMES = makeSymbolicNames()
        val VOCABULARY: Vocabulary = VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES)


        @Deprecated("Use {@link #VOCABULARY} instead.")
        val tokenNames: Array<String?>

        init {
            tokenNames = arrayOfNulls(_SYMBOLIC_NAMES.size)
            for (i in tokenNames.indices) {
                tokenNames[i] = VOCABULARY.getLiteralName(i)
                if (tokenNames[i] == null) {
                    tokenNames[i] = VOCABULARY.getSymbolicName(i)
                }

                if (tokenNames[i] == null) {
                    tokenNames[i] = "<INVALID>"
                }
            }
        }

        const val _serializedATN: String =
            "\u0004\u0000\'\u00de\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001" +
                    "\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004" +
                    "\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007" +
                    "\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b" +
                    "\u0007\u000b\u0002\u000c\u0007\u000c\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002" +
                    "\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002" +
                    "\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002" +
                    "\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002" +
                    "\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002" +
                    "\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002" +
                    "\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007" +
                    "!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007" +
                    "&\u0002\'\u0007\'\u0002(\u0007(\u0001\u0000\u0001\u0000\u0001\u0001\u0001" +
                    "\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001" +
                    "\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001" +
                    "\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001" +
                    "\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001" +
                    "\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n" +
                    "\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001" +
                    "\u000b\u0001\u000b\u0001\u000b\u0001\u000c\u0001\u000c\u0001\r\u0001\r\u0001\u000e" +
                    "\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011" +
                    "\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012" +
                    "\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015" +
                    "\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017" +
                    "\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019" +
                    "\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b" +
                    "\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d" +
                    "\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001!" +
                    "\u0001!\u0001!\u0001\"\u0001\"\u0005\"\u00bb\b\"\n\"\u000c\"\u00be\t\"\u0001" +
                    "#\u0004#\u00c1\b#\u000b#\u000c#\u00c2\u0001$\u0001$\u0003$\u00c7\b$\u0001" +
                    "$\u0001$\u0001%\u0001%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001" +
                    "&\u0001\'\u0004\'\u00d5\b\'\u000b\'\u000c\'\u00d6\u0001\'\u0001\'\u0001(\u0001" +
                    "(\u0001(\u0001(\u0000\u0000)\u0001\u0001\u0003\u0002\u0005\u0003\u0007" +
                    "\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b" +
                    "\u0017\u000c\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013" +
                    "\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d" +
                    ";\u001e=\u001f? A!C\"E#G\$I\u0000K\u0000M%O&Q\'\u0001\u0000\u0005\u0003" +
                    "\u0000AZ__az\u0004\u000009AZ__az\u0001\u000009\u0003\u0000\t\t\u000c\r  \u0001" +
                    "\u0000\n\n\u00df\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001" +
                    "\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001" +
                    "\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000" +
                    "\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000" +
                    "\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000" +
                    "\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000" +
                    "\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000" +
                    "\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000" +
                    "\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000" +
                    "%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001" +
                    "\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000" +
                    "\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u0000" +
                    "3\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001" +
                    "\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000" +
                    "\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000" +
                    "A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000E\u0001" +
                    "\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000\u0000M\u0001\u0000\u0000" +
                    "\u0000\u0000O\u0001\u0000\u0000\u0000\u0000Q\u0001\u0000\u0000\u0000\u0001" +
                    "S\u0001\u0000\u0000\u0000\u0003U\u0001\u0000\u0000\u0000\u0005W\u0001" +
                    "\u0000\u0000\u0000\u0007[\u0001\u0000\u0000\u0000\t^\u0001\u0000\u0000" +
                    "\u0000\u000bc\u0001\u0000\u0000\u0000\rj\u0001\u0000\u0000\u0000\u000f" +
                    "o\u0001\u0000\u0000\u0000\u0011q\u0001\u0000\u0000\u0000\u0013s\u0001" +
                    "\u0000\u0000\u0000\u0015u\u0001\u0000\u0000\u0000\u0017{\u0001\u0000\u0000" +
                    "\u0000\u0019\u0080\u0001\u0000\u0000\u0000\u001b\u0082\u0001\u0000\u0000" +
                    "\u0000\u001d\u0084\u0001\u0000\u0000\u0000\u001f\u0086\u0001\u0000\u0000" +
                    "\u0000!\u0088\u0001\u0000\u0000\u0000#\u008a\u0001\u0000\u0000\u0000%" +
                    "\u008e\u0001\u0000\u0000\u0000\'\u0091\u0001\u0000\u0000\u0000)\u0094" +
                    "\u0001\u0000\u0000\u0000+\u0096\u0001\u0000\u0000\u0000-\u0099\u0001\u0000" +
                    "\u0000\u0000/\u009b\u0001\u0000\u0000\u00001\u009e\u0001\u0000\u0000\u0000" +
                    "3\u00a1\u0001\u0000\u0000\u00005\u00a5\u0001\u0000\u0000\u00007\u00a8" +
                    "\u0001\u0000\u0000\u00009\u00aa\u0001\u0000\u0000\u0000;\u00ac\u0001\u0000" +
                    "\u0000\u0000=\u00af\u0001\u0000\u0000\u0000?\u00b1\u0001\u0000\u0000\u0000" +
                    "A\u00b3\u0001\u0000\u0000\u0000C\u00b5\u0001\u0000\u0000\u0000E\u00b8" +
                    "\u0001\u0000\u0000\u0000G\u00c0\u0001\u0000\u0000\u0000I\u00c4\u0001\u0000" +
                    "\u0000\u0000K\u00ca\u0001\u0000\u0000\u0000M\u00cc\u0001\u0000\u0000\u0000" +
                    "O\u00d4\u0001\u0000\u0000\u0000Q\u00da\u0001\u0000\u0000\u0000ST\u0005" +
                    ";\u0000\u0000T\u0002\u0001\u0000\u0000\u0000UV\u0005=\u0000\u0000V\u0004" +
                    "\u0001\u0000\u0000\u0000WX\u0005e\u0000\u0000XY\u0005n\u0000\u0000YZ\u0005" +
                    "d\u0000\u0000Z\u0006\u0001\u0000\u0000\u0000[\\\u0005i\u0000\u0000\\]" +
                    "\u0005f\u0000\u0000]\b\u0001\u0000\u0000\u0000^_\u0005t\u0000\u0000_`" +
                    "\u0005h\u0000\u0000`a\u0005e\u0000\u0000ab\u0005n\u0000\u0000b\n\u0001" +
                    "\u0000\u0000\u0000cd\u0005e\u0000\u0000de\u0005l\u0000\u0000ef\u0005s" +
                    "\u0000\u0000fg\u0005e\u0000\u0000gh\u0005i\u0000\u0000hi\u0005f\u0000" +
                    "\u0000i\u000c\u0001\u0000\u0000\u0000jk\u0005e\u0000\u0000kl\u0005l\u0000" +
                    "\u0000lm\u0005s\u0000\u0000mn\u0005e\u0000\u0000n\u000e\u0001\u0000\u0000" +
                    "\u0000op\u0005,\u0000\u0000p\u0010\u0001\u0000\u0000\u0000qr\u0005<\u0000" +
                    "\u0000r\u0012\u0001\u0000\u0000\u0000st\u0005>\u0000\u0000t\u0014\u0001" +
                    "\u0000\u0000\u0000uv\u0005f\u0000\u0000vw\u0005a\u0000\u0000wx\u0005l" +
                    "\u0000\u0000xy\u0005s\u0000\u0000yz\u0005e\u0000\u0000z\u0016\u0001\u0000" +
                    "\u0000\u0000{|\u0005t\u0000\u0000|}\u0005r\u0000\u0000}~\u0005u\u0000" +
                    "\u0000~\u007f\u0005e\u0000\u0000\u007f\u0018\u0001\u0000\u0000\u0000\u0080" +
                    "\u0081\u0005.\u0000\u0000\u0081\u001a\u0001\u0000\u0000\u0000\u0082\u0083" +
                    "\u0005~\u0000\u0000\u0083\u001c\u0001\u0000\u0000\u0000\u0084\u0085\u0005" +
                    "-\u0000\u0000\u0085\u001e\u0001\u0000\u0000\u0000\u0086\u0087\u0005(\u0000" +
                    "\u0000\u0087 \u0001\u0000\u0000\u0000\u0088\u0089\u0005)\u0000\u0000\u0089" +
                    "\"\u0001\u0000\u0000\u0000\u008a\u008b\u0005n\u0000\u0000\u008b\u008c" +
                    "\u0005o\u0000\u0000\u008c\u008d\u0005t\u0000\u0000\u008d$\u0001\u0000" +
                    "\u0000\u0000\u008e\u008f\u0005<\u0000\u0000\u008f\u0090\u0005<\u0000\u0000" +
                    "\u0090&\u0001\u0000\u0000\u0000\u0091\u0092\u0005>\u0000\u0000\u0092\u0093" +
                    "\u0005>\u0000\u0000\u0093(\u0001\u0000\u0000\u0000\u0094\u0095\u0005&" +
                    "\u0000\u0000\u0095*\u0001\u0000\u0000\u0000\u0096\u0097\u0005/\u0000\u0000" +
                    "\u0097\u0098\u0005/\u0000\u0000\u0098,\u0001\u0000\u0000\u0000\u0099\u009a" +
                    "\u0005%\u0000\u0000\u009a.\u0001\u0000\u0000\u0000\u009b\u009c\u0005<" +
                    "\u0000\u0000\u009c\u009d\u0005=\u0000\u0000\u009d0\u0001\u0000\u0000\u0000" +
                    "\u009e\u009f\u0005>\u0000\u0000\u009f\u00a0\u0005=\u0000\u0000\u00a02" +
                    "\u0001\u0000\u0000\u0000\u00a1\u00a2\u0005a\u0000\u0000\u00a2\u00a3\u0005" +
                    "n\u0000\u0000\u00a3\u00a4\u0005d\u0000\u0000\u00a44\u0001\u0000\u0000" +
                    "\u0000\u00a5\u00a6\u0005o\u0000\u0000\u00a6\u00a7\u0005r\u0000\u0000\u00a7" +
                    "6\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005+\u0000\u0000\u00a98\u0001" +
                    "\u0000\u0000\u0000\u00aa\u00ab\u0005*\u0000\u0000\u00ab:\u0001\u0000\u0000" +
                    "\u0000\u00ac\u00ad\u0005=\u0000\u0000\u00ad\u00ae\u0005=\u0000\u0000\u00ae" +
                    "<\u0001\u0000\u0000\u0000\u00af\u00b0\u0005|\u0000\u0000\u00b0>\u0001" +
                    "\u0000\u0000\u0000\u00b1\u00b2\u0005^\u0000\u0000\u00b2@\u0001\u0000\u0000" +
                    "\u0000\u00b3\u00b4\u0005/\u0000\u0000\u00b4B\u0001\u0000\u0000\u0000\u00b5" +
                    "\u00b6\u0005~\u0000\u0000\u00b6\u00b7\u0005=\u0000\u0000\u00b7D\u0001" +
                    "\u0000\u0000\u0000\u00b8\u00bc\u0007\u0000\u0000\u0000\u00b9\u00bb\u0007" +
                    "\u0001\u0000\u0000\u00ba\u00b9\u0001\u0000\u0000\u0000\u00bb\u00be\u0001" +
                    "\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001" +
                    "\u0000\u0000\u0000\u00bdF\u0001\u0000\u0000\u0000\u00be\u00bc\u0001\u0000" +
                    "\u0000\u0000\u00bf\u00c1\u0003K%\u0000\u00c0\u00bf\u0001\u0000\u0000\u0000" +
                    "\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000" +
                    "\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3H\u0001\u0000\u0000\u0000\u00c4" +
                    "\u00c6\u0005\\\u0000\u0000\u00c5\u00c7\u0005\r\u0000\u0000\u00c6\u00c5" +
                    "\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00c8" +
                    "\u0001\u0000\u0000\u0000\u00c8\u00c9\u0005\n\u0000\u0000\u00c9J\u0001" +
                    "\u0000\u0000\u0000\u00ca\u00cb\u0007\u0002\u0000\u0000\u00cbL\u0001\u0000" +
                    "\u0000\u0000\u00cc\u00cd\u0005-\u0000\u0000\u00cd\u00ce\u0005-\u0000\u0000" +
                    "\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf\u00d0\u0006&\u0000\u0000\u00d0" +
                    "\u00d1\u0001\u0000\u0000\u0000\u00d1\u00d2\u0006&\u0001\u0000\u00d2N\u0001" +
                    "\u0000\u0000\u0000\u00d3\u00d5\u0007\u0003\u0000\u0000\u00d4\u00d3\u0001" +
                    "\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00d4\u0001" +
                    "\u0000\u0000\u0000\u00d6\u00d7\u0001\u0000\u0000\u0000\u00d7\u00d8\u0001" +
                    "\u0000\u0000\u0000\u00d8\u00d9\u0006\'\u0001\u0000\u00d9P\u0001\u0000" +
                    "\u0000\u0000\u00da\u00db\u0007\u0004\u0000\u0000\u00db\u00dc\u0001\u0000" +
                    "\u0000\u0000\u00dc\u00dd\u0006(\u0002\u0000\u00ddR\u0001\u0000\u0000\u0000" +
                    "\u0005\u0000\u00bc\u00c2\u00c6\u00d6\u0003\u0001&\u0000\u0000\u0001\u0000" +
                    "\u0000\u0002\u0000"
        val _ATN: ATN = ATNDeserializer().deserialize(_serializedATN.toCharArray())

        init {
            _decisionToDFA = arrayOfNulls(_ATN.numberOfDecisions)
            for (i in 0 until _ATN.numberOfDecisions) {
                _decisionToDFA[i] = DFA(_ATN.getDecisionState(i), i)
            }
        }
    }
}