import org.antlr.v4.runtime.*

abstract class LuaLexerBase protected constructor(input: CharStream?) :
    Lexer(input) {
    private var startLine = 0
    private var startCol = 0

    protected fun HandleComment() {
        startLine = this.line
        startCol = this.charPositionInLine - 2
        val cs: CharStream = _input as CharStream
        if (cs.LA(1) == '['.code) {
            val sep = skipSep(cs)
            if (sep >= 2) {
                readLongString(cs, sep)
                return
            }
        }
        while (cs.LA(1) != '\n'.code && cs.LA(1) != -1) {
            cs.consume()
        }
    }

    private fun readLongString(cs: CharStream, sep: Int) {
        var done = false
        cs.consume()
        while (true) {
            val c: Int = cs.LA(1)
            val cc = c.toChar()
            when  {
                c == -1 -> {
                    done = true
                    val listener: ANTLRErrorListener = this.errorListenerDispatch
                    listener.syntaxError(this, null, this.startLine, this.startCol, "unfinished long comment", null)
                }

                cc == ']' -> if (skipSep(cs) == sep) {
                    cs.consume()
                    done = true
                }

                else -> {
                    if (cs.LA(1) == -1) {
                        break
                    }
                    cs.consume()
                }
            }
            if (done) break
        }
    }

    private fun skipSep(cs: CharStream): Int {
        var count = 0
        val s: Int = cs.LA(1)
        cs.consume()
        while (cs.LA(1) == '='.code) {
            cs.consume()
            count++
        }
        if (cs.LA(1) == s) count += 2
        else if (count == 0) count = 1
        else count = 0
        return count
    }
    
    /* removed by loloof64
    public boolean IsLine1Col0()
    {
        CharStream cs = (CharStream)_input;
        if (cs.index() == 1) return true;
        return false;
    }
    */
}
