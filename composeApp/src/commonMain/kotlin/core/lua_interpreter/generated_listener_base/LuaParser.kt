import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATN
import org.antlr.v4.runtime.atn.ATNDeserializer
import org.antlr.v4.runtime.atn.ParserATNSimulator
import org.antlr.v4.runtime.atn.PredictionContextCache
import org.antlr.v4.runtime.dfa.DFA
import org.antlr.v4.runtime.tree.ParseTreeVisitor
import org.antlr.v4.runtime.tree.TerminalNode

@Suppress("unused")
class LuaParser(input: TokenStream?) : Parser(input) {
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

    override fun getATN(): ATN {
        return _ATN
    }

    class Start_Context(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun chunk(): ChunkContext {
            return getRuleContext(ChunkContext::class.java, 0)
        }

        fun EOF(): TerminalNode {
            return getToken(Recognizer.EOF, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_start_
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitStart_(this)
            else visitor.visitChildren(this)
        }
    }

    @Throws(RecognitionException::class)
    fun start_(): Start_Context {
        val _localctx = Start_Context(_ctx, state)
        enterRule(_localctx, 0, RULE_start_)
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 22
                chunk()
                state = 23
                match(Recognizer.EOF)
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class ChunkContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun block(): BlockContext {
            return getRuleContext(BlockContext::class.java, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_chunk
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitChunk(this)
            else visitor.visitChildren(this)
        }
    }

    @Throws(RecognitionException::class)
    fun chunk(): ChunkContext {
        val _localctx = ChunkContext(_ctx, state)
        enterRule(_localctx, 2, RULE_chunk)
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 25
                block()
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class BlockContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun stat(): List<StatContext> {
            return getRuleContexts(StatContext::class.java)
        }

        fun stat(i: Int): StatContext {
            return getRuleContext(StatContext::class.java, i)
        }

        override fun getRuleIndex(): Int {
            return RULE_block
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitBlock(this)
            else visitor.visitChildren(this)
        }
    }

    @Throws(RecognitionException::class)
    fun block(): BlockContext {
        val _localctx = BlockContext(_ctx, state)
        enterRule(_localctx, 4, RULE_block)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 30
                _errHandler.sync(this)
                _la = _input.LA(1)
                while ((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 34359738386L) != 0L)) {
                    run {
                        run {
                            state = 27
                            stat()
                        }
                    }
                    state = 32
                    _errHandler.sync(this)
                    _la = _input.LA(1)
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    open class StatContext : ParserRuleContext {
        constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState)

        override fun getRuleIndex(): Int {
            return RULE_stat
        }

        constructor()

        fun copyFrom(ctx: StatContext?) {
            super.copyFrom(ctx)
        }
    }

    class SemiColumnExecContext(ctx: StatContext?) : StatContext() {
        fun SEMI(): TerminalNode {
            return getToken(SEMI, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitSemiColumnExec(this)
            else visitor.visitChildren(this)
        }
    }

    class IfExecContext(ctx: StatContext?) : StatContext() {
        fun ifstat(): IfstatContext {
            return getRuleContext(IfstatContext::class.java, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitIfExec(this)
            else visitor.visitChildren(this)
        }
    }

    class AssignExecContext(ctx: StatContext?) : StatContext() {
        fun assign(): AssignContext {
            return getRuleContext(AssignContext::class.java, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitAssignExec(this)
            else visitor.visitChildren(this)
        }
    }

    @Throws(RecognitionException::class)
    fun stat(): StatContext {
        var _localctx = StatContext(_ctx, state)
        enterRule(_localctx, 6, RULE_stat)
        try {
            state = 36
            _errHandler.sync(this)
            when (_input.LA(1)) {
                SEMI -> {
                    _localctx = SemiColumnExecContext(_localctx)
                    enterOuterAlt(_localctx, 1)
                    run {
                        state = 33
                        match(SEMI)
                    }
                }

                NAME -> {
                    _localctx = AssignExecContext(_localctx)
                    enterOuterAlt(_localctx, 2)
                    run {
                        state = 34
                        assign()
                    }
                }

                IF -> {
                    _localctx = IfExecContext(_localctx)
                    enterOuterAlt(_localctx, 3)
                    run {
                        state = 35
                        ifstat()
                    }
                }

                else -> throw NoViableAltException(this)
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class AssignContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun namelist(): NamelistContext {
            return getRuleContext(NamelistContext::class.java, 0)
        }

        fun EQ(): TerminalNode {
            return getToken(EQ, 0)
        }

        fun explist(): ExplistContext {
            return getRuleContext(ExplistContext::class.java, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_assign
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitAssign(this)
            else visitor.visitChildren(this)
        }
    }

    @Throws(RecognitionException::class)
    fun assign(): AssignContext {
        val _localctx = AssignContext(_ctx, state)
        enterRule(_localctx, 8, RULE_assign)
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 38
                namelist()
                state = 39
                match(EQ)
                state = 40
                explist()
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class IfstatContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        var endExec: BlockContext? = null
        fun IF(): TerminalNode {
            return getToken(IF, 0)
        }

        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun THEN(): List<TerminalNode> {
            return getTokens(THEN)
        }

        fun THEN(i: Int): TerminalNode {
            return getToken(THEN, i)
        }

        fun block(): List<BlockContext> {
            return getRuleContexts(BlockContext::class.java)
        }

        fun block(i: Int): BlockContext {
            return getRuleContext(BlockContext::class.java, i)
        }

        fun END(): TerminalNode {
            return getToken(END, 0)
        }

        fun ELSEIF(): List<TerminalNode> {
            return getTokens(ELSEIF)
        }

        fun ELSEIF(i: Int): TerminalNode {
            return getToken(ELSEIF, i)
        }

        fun ELSE(): TerminalNode {
            return getToken(ELSE, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_ifstat
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitIfstat(this)
            else visitor.visitChildren(this)
        }
    }

    @Throws(RecognitionException::class)
    fun ifstat(): IfstatContext {
        val _localctx = IfstatContext(_ctx, state)
        enterRule(_localctx, 10, RULE_ifstat)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 42
                match(IF)
                state = 43
                exp(0)
                state = 44
                match(THEN)
                state = 45
                block()
                state = 53
                _errHandler.sync(this)
                _la = _input.LA(1)
                while (_la == ELSEIF) {
                    run {
                        run {
                            state = 46
                            match(ELSEIF)
                            state = 47
                            exp(0)
                            state = 48
                            match(THEN)
                            state = 49
                            block()
                        }
                    }
                    state = 55
                    _errHandler.sync(this)
                    _la = _input.LA(1)
                }
                state = 58
                _errHandler.sync(this)
                _la = _input.LA(1)
                if (_la == ELSE) {
                    run {
                        state = 56
                        match(ELSE)
                        state = 57
                        _localctx.endExec = block()
                    }
                }

                state = 60
                match(END)
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class NamelistContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun NAME(): List<TerminalNode> {
            return getTokens(NAME)
        }

        fun NAME(i: Int): TerminalNode {
            return getToken(NAME, i)
        }

        fun COMMA(): List<TerminalNode> {
            return getTokens(COMMA)
        }

        fun COMMA(i: Int): TerminalNode {
            return getToken(COMMA, i)
        }

        override fun getRuleIndex(): Int {
            return RULE_namelist
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitNamelist(this)
            else visitor.visitChildren(this)
        }
    }

    @Throws(RecognitionException::class)
    fun namelist(): NamelistContext {
        val _localctx = NamelistContext(_ctx, state)
        enterRule(_localctx, 12, RULE_namelist)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 62
                match(NAME)
                state = 67
                _errHandler.sync(this)
                _la = _input.LA(1)
                while (_la == COMMA) {
                    run {
                        run {
                            state = 63
                            match(COMMA)
                            state = 64
                            match(NAME)
                        }
                    }
                    state = 69
                    _errHandler.sync(this)
                    _la = _input.LA(1)
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class ExplistContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun COMMA(): List<TerminalNode> {
            return getTokens(COMMA)
        }

        fun COMMA(i: Int): TerminalNode {
            return getToken(COMMA, i)
        }

        override fun getRuleIndex(): Int {
            return RULE_explist
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitExplist(this)
            else visitor.visitChildren(this)
        }
    }

    @Throws(RecognitionException::class)
    fun explist(): ExplistContext {
        val _localctx = ExplistContext(_ctx, state)
        enterRule(_localctx, 14, RULE_explist)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 70
                exp(0)
                state = 75
                _errHandler.sync(this)
                _la = _input.LA(1)
                while (_la == COMMA) {
                    run {
                        run {
                            state = 71
                            match(COMMA)
                            state = 72
                            exp(0)
                        }
                    }
                    state = 77
                    _errHandler.sync(this)
                    _la = _input.LA(1)
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    open class ExpContext : ParserRuleContext {
        constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState)

        override fun getRuleIndex(): Int {
            return RULE_exp
        }

        constructor()

        fun copyFrom(ctx: ExpContext?) {
            super.copyFrom(ctx)
        }
    }

    class PrefixExprContext(ctx: ExpContext?) : ExpContext() {
        fun prefix(): PrefixContext {
            return getRuleContext(PrefixContext::class.java, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitPrefixExpr(this)
            else visitor.visitChildren(this)
        }
    }

    class UnaryExprContext(ctx: ExpContext?) : ExpContext() {
        var op: Token? = null
        fun exp(): ExpContext {
            return getRuleContext(ExpContext::class.java, 0)
        }

        fun NOT(): TerminalNode {
            return getToken(NOT, 0)
        }

        fun MINUS(): TerminalNode {
            return getToken(MINUS, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitUnaryExpr(this)
            else visitor.visitChildren(this)
        }
    }

    class ExponentExprContext(ctx: ExpContext?) : ExpContext() {
        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun CARET(): TerminalNode {
            return getToken(CARET, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitExponentExpr(this)
            else visitor.visitChildren(this)
        }
    }

    class TrueExprContext(ctx: ExpContext?) : ExpContext() {
        fun TRUE(): TerminalNode {
            return getToken(TRUE, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitTrueExpr(this)
            else visitor.visitChildren(this)
        }
    }

    class NumberExprContext(ctx: ExpContext?) : ExpContext() {
        fun number(): NumberContext {
            return getRuleContext(NumberContext::class.java, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitNumberExpr(this)
            else visitor.visitChildren(this)
        }
    }

    class PlusMinusExprContext(ctx: ExpContext?) : ExpContext() {
        var op: Token? = null
        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun PLUS(): TerminalNode {
            return getToken(PLUS, 0)
        }

        fun MINUS(): TerminalNode {
            return getToken(MINUS, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitPlusMinusExpr(this)
            else visitor.visitChildren(this)
        }
    }

    class BooleanAndExprContext(ctx: ExpContext?) : ExpContext() {
        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun AND(): TerminalNode {
            return getToken(AND, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitBooleanAndExpr(this)
            else visitor.visitChildren(this)
        }
    }

    class BooleanOrExprContext(ctx: ExpContext?) : ExpContext() {
        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun OR(): TerminalNode {
            return getToken(OR, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitBooleanOrExpr(this)
            else visitor.visitChildren(this)
        }
    }

    class MulDivModuloExprContext(ctx: ExpContext?) : ExpContext() {
        var op: Token? = null
        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun STAR(): TerminalNode {
            return getToken(STAR, 0)
        }

        fun SLASH(): TerminalNode {
            return getToken(SLASH, 0)
        }

        fun PER(): TerminalNode {
            return getToken(PER, 0)
        }

        fun SS(): TerminalNode {
            return getToken(SS, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitMulDivModuloExpr(this)
            else visitor.visitChildren(this)
        }
    }

    class IntBinaryLogicalExprContext(ctx: ExpContext?) : ExpContext() {
        var op: Token? = null
        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun AMP(): TerminalNode {
            return getToken(AMP, 0)
        }

        fun PIPE(): TerminalNode {
            return getToken(PIPE, 0)
        }

        fun SQUIG(): TerminalNode {
            return getToken(SQUIG, 0)
        }

        fun LL(): TerminalNode {
            return getToken(LL, 0)
        }

        fun GG(): TerminalNode {
            return getToken(GG, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitIntBinaryLogicalExpr(this)
            else visitor.visitChildren(this)
        }
    }

    class FalseExprContext(ctx: ExpContext?) : ExpContext() {
        fun FALSE(): TerminalNode {
            return getToken(FALSE, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitFalseExpr(this)
            else visitor.visitChildren(this)
        }
    }

    class BooleanBinaryLogicalExprContext(ctx: ExpContext?) : ExpContext() {
        var op: Token? = null
        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun LT(): TerminalNode {
            return getToken(LT, 0)
        }

        fun GT(): TerminalNode {
            return getToken(GT, 0)
        }

        fun LE(): TerminalNode {
            return getToken(LE, 0)
        }

        fun GE(): TerminalNode {
            return getToken(GE, 0)
        }

        fun SQEQ(): TerminalNode {
            return getToken(SQEQ, 0)
        }

        fun EE(): TerminalNode {
            return getToken(EE, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitBooleanBinaryLogicalExpr(this)
            else visitor.visitChildren(this)
        }
    }

    @Throws(RecognitionException::class)
    fun exp(): ExpContext {
        return exp(0)
    }

    @Throws(RecognitionException::class)
    private fun exp(_p: Int): ExpContext {
        val _parentctx = _ctx
        val _parentState = state
        var _localctx = ExpContext(_ctx, _parentState)
        var _prevctx = _localctx
        val _startState = 16
        enterRecursionRule(_localctx, 16, RULE_exp, _p)
        var _la: Int
        try {
            var _alt: Int
            enterOuterAlt(_localctx, 1)
            run {
                state = 85
                _errHandler.sync(this)
                when (_input.LA(1)) {
                    FALSE -> {
                        _localctx = FalseExprContext(_localctx)
                        _ctx = _localctx
                        _prevctx = _localctx

                        state = 79
                        match(FALSE)
                    }

                    TRUE -> {
                        _localctx = TrueExprContext(_localctx)
                        _ctx = _localctx
                        _prevctx = _localctx
                        state = 80
                        match(TRUE)
                    }

                    INT -> {
                        _localctx = NumberExprContext(_localctx)
                        _ctx = _localctx
                        _prevctx = _localctx
                        state = 81
                        number()
                    }

                    OP, NAME -> {
                        _localctx = PrefixExprContext(_localctx)
                        _ctx = _localctx
                        _prevctx = _localctx
                        state = 82
                        prefix()
                    }

                    MINUS, NOT -> {
                        _localctx = UnaryExprContext(_localctx)
                        _ctx = _localctx
                        _prevctx = _localctx
                        state = 83
                        (_localctx as UnaryExprContext).op = _input.LT(1)
                        _la = _input.LA(1)
                        if (!(_la == MINUS || _la == NOT)) {
                            (_localctx as UnaryExprContext).op = _errHandler.recoverInline(this) as Token
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true
                            _errHandler.reportMatch(this)
                            consume()
                        }
                        state = 84
                        exp(7)
                    }

                    else -> throw NoViableAltException(this)
                }
                _ctx.stop = _input.LT(-1)
                state = 110
                _errHandler.sync(this)
                _alt = interpreter.adaptivePredict(_input, 8, _ctx)
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent()
                        _prevctx = _localctx
                        run {
                            state = 108
                            _errHandler.sync(this)
                            when (interpreter.adaptivePredict(_input, 7, _ctx)) {
                                1 -> {
                                    _localctx = ExponentExprContext(ExpContext(_parentctx, _parentState))
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 87
                                    if (!(precpred(_ctx, 8))) throw FailedPredicateException(this, "precpred(_ctx, 8)")
                                    run {
                                        state = 88
                                        match(CARET)
                                    }
                                    state = 89
                                    exp(8)
                                }

                                2 -> {
                                    _localctx = MulDivModuloExprContext(ExpContext(_parentctx, _parentState))
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 90
                                    if (!(precpred(_ctx, 6))) throw FailedPredicateException(this, "precpred(_ctx, 6)")
                                    state = 91
                                    (_localctx as MulDivModuloExprContext).op = _input.LT(1)
                                    _la = _input.LA(1)
                                    if (!((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 9139388416L) != 0L))) {
                                        (_localctx as MulDivModuloExprContext).op =
                                            _errHandler.recoverInline(this) as Token
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true
                                        _errHandler.reportMatch(this)
                                        consume()
                                    }
                                    state = 92
                                    exp(7)
                                }

                                3 -> {
                                    _localctx = PlusMinusExprContext(ExpContext(_parentctx, _parentState))
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 93
                                    if (!(precpred(_ctx, 5))) throw FailedPredicateException(this, "precpred(_ctx, 5)")
                                    state = 94
                                    (_localctx as PlusMinusExprContext).op = _input.LT(1)
                                    _la = _input.LA(1)
                                    if (!(_la == MINUS || _la == PLUS)) {
                                        (_localctx as PlusMinusExprContext).op =
                                            _errHandler.recoverInline(this) as Token
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true
                                        _errHandler.reportMatch(this)
                                        consume()
                                    }
                                    state = 95
                                    exp(6)
                                }

                                4 -> {
                                    _localctx = BooleanBinaryLogicalExprContext(ExpContext(_parentctx, _parentState))
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 96
                                    if (!(precpred(_ctx, 4))) throw FailedPredicateException(this, "precpred(_ctx, 4)")
                                    state = 97
                                    (_localctx as BooleanBinaryLogicalExprContext).op = _input.LT(1)
                                    _la = _input.LA(1)
                                    if (!((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 18303944192L) != 0L))) {
                                        (_localctx as BooleanBinaryLogicalExprContext).op =
                                            _errHandler.recoverInline(this) as Token
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true
                                        _errHandler.reportMatch(this)
                                        consume()
                                    }
                                    state = 98
                                    exp(5)
                                }

                                5 -> {
                                    _localctx = BooleanAndExprContext(ExpContext(_parentctx, _parentState))
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 99
                                    if (!(precpred(_ctx, 3))) throw FailedPredicateException(this, "precpred(_ctx, 3)")
                                    run {
                                        state = 100
                                        match(AND)
                                    }
                                    state = 101
                                    exp(4)
                                }

                                6 -> {
                                    _localctx = BooleanOrExprContext(ExpContext(_parentctx, _parentState))
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 102
                                    if (!(precpred(_ctx, 2))) throw FailedPredicateException(this, "precpred(_ctx, 2)")
                                    run {
                                        state = 103
                                        match(OR)
                                    }
                                    state = 104
                                    exp(3)
                                }

                                7 -> {
                                    _localctx = IntBinaryLogicalExprContext(ExpContext(_parentctx, _parentState))
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 105
                                    if (!(precpred(_ctx, 1))) throw FailedPredicateException(this, "precpred(_ctx, 1)")
                                    state = 106
                                    (_localctx as IntBinaryLogicalExprContext).op = _input.LT(1)
                                    _la = _input.LA(1)
                                    if (!((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 2151170048L) != 0L))) {
                                        (_localctx as IntBinaryLogicalExprContext).op =
                                            _errHandler.recoverInline(this) as Token
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true
                                        _errHandler.reportMatch(this)
                                        consume()
                                    }
                                    state = 107
                                    exp(2)
                                }

                                else -> {}
                            }
                        }
                    }
                    state = 112
                    _errHandler.sync(this)
                    _alt = interpreter.adaptivePredict(_input, 8, _ctx)
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            unrollRecursionContexts(_parentctx)
        }
        return _localctx
    }

    open class PrefixContext : ParserRuleContext {
        constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState)

        override fun getRuleIndex(): Int {
            return RULE_prefix
        }

        constructor()

        fun copyFrom(ctx: PrefixContext?) {
            super.copyFrom(ctx)
        }
    }

    class ParenthesisPrefixContext(ctx: PrefixContext?) : PrefixContext() {
        fun OP(): TerminalNode {
            return getToken(OP, 0)
        }

        fun exp(): ExpContext {
            return getRuleContext(ExpContext::class.java, 0)
        }

        fun CP(): TerminalNode {
            return getToken(CP, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitParenthesisPrefix(this)
            else visitor.visitChildren(this)
        }
    }

    class VariablePrefixContext(ctx: PrefixContext?) : PrefixContext() {
        fun NAME(): TerminalNode {
            return getToken(NAME, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitVariablePrefix(this)
            else visitor.visitChildren(this)
        }
    }

    @Throws(RecognitionException::class)
    fun prefix(): PrefixContext {
        var _localctx = PrefixContext(_ctx, state)
        enterRule(_localctx, 18, RULE_prefix)
        try {
            state = 118
            _errHandler.sync(this)
            when (_input.LA(1)) {
                NAME -> {
                    _localctx = VariablePrefixContext(_localctx)
                    enterOuterAlt(_localctx, 1)
                    run {
                        state = 113
                        match(NAME)
                    }
                }

                OP -> {
                    _localctx = ParenthesisPrefixContext(_localctx)
                    enterOuterAlt(_localctx, 2)
                    run {
                        state = 114
                        match(OP)
                        state = 115
                        exp(0)
                        state = 116
                        match(CP)
                    }
                }

                else -> throw NoViableAltException(this)
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    open class NumberContext : ParserRuleContext {
        constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState)

        override fun getRuleIndex(): Int {
            return RULE_number
        }

        constructor()

        fun copyFrom(ctx: NumberContext?) {
            super.copyFrom(ctx)
        }
    }

    class IntegerValueContext(ctx: NumberContext?) : NumberContext() {
        fun INT(): TerminalNode {
            return getToken(INT, 0)
        }

        init {
            copyFrom(ctx)
        }

        override fun <T> accept(visitor: ParseTreeVisitor<out T>): T {
            return if (visitor is LuaVisitor<*>) (visitor as LuaVisitor<out T>).visitIntegerValue(this)
            else visitor.visitChildren(this)
        }
    }

    @Throws(RecognitionException::class)
    fun number(): NumberContext {
        var _localctx = NumberContext(_ctx, state)
        enterRule(_localctx, 20, RULE_number)
        try {
            _localctx = IntegerValueContext(_localctx)
            enterOuterAlt(_localctx, 1)
            run {
                state = 120
                match(INT)
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    override fun sempred(_localctx: RuleContext, ruleIndex: Int, predIndex: Int): Boolean {
        when (ruleIndex) {
            8 -> return exp_sempred(_localctx as ExpContext, predIndex)
        }
        return true
    }

    private fun exp_sempred(_localctx: ExpContext, predIndex: Int): Boolean {
        when (predIndex) {
            0 -> return precpred(_ctx, 8)
            1 -> return precpred(_ctx, 6)
            2 -> return precpred(_ctx, 5)
            3 -> return precpred(_ctx, 4)
            4 -> return precpred(_ctx, 3)
            5 -> return precpred(_ctx, 2)
            6 -> return precpred(_ctx, 1)
        }
        return true
    }

    init {
        _interp = ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache)
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
        const val RULE_start_: Int = 0
        const val RULE_chunk: Int = 1
        const val RULE_block: Int = 2
        const val RULE_stat: Int = 3
        const val RULE_assign: Int = 4
        const val RULE_ifstat: Int = 5
        const val RULE_namelist: Int = 6
        const val RULE_explist: Int = 7
        const val RULE_exp: Int = 8
        const val RULE_prefix: Int = 9
        const val RULE_number: Int = 10
        private fun makeRuleNames(): Array<String> {
            return arrayOf(
                "start_", "chunk", "block", "stat", "assign", "ifstat", "namelist", "explist",
                "exp", "prefix", "number"
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

        const val _serializedATN: String = "\u0004\u0001\'{\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002" +
                "\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002" +
                "\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002" +
                "\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000\u0001\u0000\u0001" +
                "\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0005\u0002\u001d\b\u0002\n" +
                "\u0002\u000c\u0002 \t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003" +
                "%\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005" +
                "\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005" +
                "\u0001\u0005\u0001\u0005\u0005\u00054\b\u0005\n\u0005\u000c\u00057\t\u0005" +
                "\u0001\u0005\u0001\u0005\u0003\u0005;\b\u0005\u0001\u0005\u0001\u0005" +
                "\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006B\b\u0006\n\u0006\u000c\u0006" +
                "E\t\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007J\b\u0007\n\u0007" +
                "\u000c\u0007M\t\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001" +
                "\b\u0003\bV\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001" +
                "\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001" +
                "\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\bm\b\b\n\b\u000c\bp\t\b\u0001" +
                "\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\tw\b\t\u0001\n\u0001\n\u0001" +
                "\n\u0000\u0001\u0010\u000b\u0000\u0002\u0004\u0006\b\n\u000c\u000e\u0010\u0012" +
                "\u0014\u0000\u0005\u0002\u0000\u000f\u000f\u0012\u0012\u0003\u0000\u0016" +
                "\u0017\u001d\u001d!!\u0002\u0000\u000f\u000f\u001c\u001c\u0004\u0000\t" +
                "\n\u0018\u0019\u001e\u001e\"\"\u0003\u0000\u000e\u000e\u0013\u0015\u001f" +
                "\u001f\u0082\u0000\u0016\u0001\u0000\u0000\u0000\u0002\u0019\u0001\u0000" +
                "\u0000\u0000\u0004\u001e\u0001\u0000\u0000\u0000\u0006$\u0001\u0000\u0000" +
                "\u0000\b&\u0001\u0000\u0000\u0000\n*\u0001\u0000\u0000\u0000\u000c>\u0001" +
                "\u0000\u0000\u0000\u000eF\u0001\u0000\u0000\u0000\u0010U\u0001\u0000\u0000" +
                "\u0000\u0012v\u0001\u0000\u0000\u0000\u0014x\u0001\u0000\u0000\u0000\u0016" +
                "\u0017\u0003\u0002\u0001\u0000\u0017\u0018\u0005\u0000\u0000\u0001\u0018" +
                "\u0001\u0001\u0000\u0000\u0000\u0019\u001a\u0003\u0004\u0002\u0000\u001a" +
                "\u0003\u0001\u0000\u0000\u0000\u001b\u001d\u0003\u0006\u0003\u0000\u001c" +
                "\u001b\u0001\u0000\u0000\u0000\u001d \u0001\u0000\u0000\u0000\u001e\u001c" +
                "\u0001\u0000\u0000\u0000\u001e\u001f\u0001\u0000\u0000\u0000\u001f\u0005" +
                "\u0001\u0000\u0000\u0000 \u001e\u0001\u0000\u0000\u0000!%\u0005\u0001" +
                "\u0000\u0000\"%\u0003\b\u0004\u0000#%\u0003\n\u0005\u0000$!\u0001\u0000" +
                "\u0000\u0000$\"\u0001\u0000\u0000\u0000$#\u0001\u0000\u0000\u0000%\u0007" +
                "\u0001\u0000\u0000\u0000&\'\u0003\u000c\u0006\u0000\'(\u0005\u0002\u0000\u0000" +
                "()\u0003\u000e\u0007\u0000)\t\u0001\u0000\u0000\u0000*+\u0005\u0004\u0000" +
                "\u0000+,\u0003\u0010\b\u0000,-\u0005\u0005\u0000\u0000-5\u0003\u0004\u0002" +
                "\u0000./\u0005\u0006\u0000\u0000/0\u0003\u0010\b\u000001\u0005\u0005\u0000" +
                "\u000012\u0003\u0004\u0002\u000024\u0001\u0000\u0000\u00003.\u0001\u0000" +
                "\u0000\u000047\u0001\u0000\u0000\u000053\u0001\u0000\u0000\u000056\u0001" +
                "\u0000\u0000\u00006:\u0001\u0000\u0000\u000075\u0001\u0000\u0000\u0000" +
                "89\u0005\u0007\u0000\u00009;\u0003\u0004\u0002\u0000:8\u0001\u0000\u0000" +
                "\u0000:;\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<=\u0005\u0003" +
                "\u0000\u0000=\u000b\u0001\u0000\u0000\u0000>C\u0005#\u0000\u0000?@\u0005" +
                "\b\u0000\u0000@B\u0005#\u0000\u0000A?\u0001\u0000\u0000\u0000BE\u0001" +
                "\u0000\u0000\u0000CA\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000" +
                "D\r\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000FK\u0003\u0010\b" +
                "\u0000GH\u0005\b\u0000\u0000HJ\u0003\u0010\b\u0000IG\u0001\u0000\u0000" +
                "\u0000JM\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000\u0000KL\u0001\u0000" +
                "\u0000\u0000L\u000f\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000" +
                "NO\u0006\b\uffff\uffff\u0000OV\u0005\u000b\u0000\u0000PV\u0005\u000c\u0000" +
                "\u0000QV\u0003\u0014\n\u0000RV\u0003\u0012\t\u0000ST\u0007\u0000\u0000" +
                "\u0000TV\u0003\u0010\b\u0007UN\u0001\u0000\u0000\u0000UP\u0001\u0000\u0000" +
                "\u0000UQ\u0001\u0000\u0000\u0000UR\u0001\u0000\u0000\u0000US\u0001\u0000" +
                "\u0000\u0000Vn\u0001\u0000\u0000\u0000WX\n\b\u0000\u0000XY\u0005 \u0000" +
                "\u0000Ym\u0003\u0010\b\bZ[\n\u0006\u0000\u0000[\\\u0007\u0001\u0000\u0000" +
                "\\m\u0003\u0010\b\u0007]^\n\u0005\u0000\u0000^_\u0007\u0002\u0000\u0000" +
                "_m\u0003\u0010\b\u0006`a\n\u0004\u0000\u0000ab\u0007\u0003\u0000\u0000" +
                "bm\u0003\u0010\b\u0005cd\n\u0003\u0000\u0000de\u0005\u001a\u0000\u0000" +
                "em\u0003\u0010\b\u0004fg\n\u0002\u0000\u0000gh\u0005\u001b\u0000\u0000" +
                "hm\u0003\u0010\b\u0003ij\n\u0001\u0000\u0000jk\u0007\u0004\u0000\u0000" +
                "km\u0003\u0010\b\u0002lW\u0001\u0000\u0000\u0000lZ\u0001\u0000\u0000\u0000" +
                "l]\u0001\u0000\u0000\u0000l`\u0001\u0000\u0000\u0000lc\u0001\u0000\u0000" +
                "\u0000lf\u0001\u0000\u0000\u0000li\u0001\u0000\u0000\u0000mp\u0001\u0000" +
                "\u0000\u0000nl\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000o\u0011" +
                "\u0001\u0000\u0000\u0000pn\u0001\u0000\u0000\u0000qw\u0005#\u0000\u0000" +
                "rs\u0005\u0010\u0000\u0000st\u0003\u0010\b\u0000tu\u0005\u0011\u0000\u0000" +
                "uw\u0001\u0000\u0000\u0000vq\u0001\u0000\u0000\u0000vr\u0001\u0000\u0000" +
                "\u0000w\u0013\u0001\u0000\u0000\u0000xy\u0005$\u0000\u0000y\u0015\u0001" +
                "\u0000\u0000\u0000\n\u001e$5:CKUlnv"
        val _ATN: ATN = ATNDeserializer().deserialize(_serializedATN.toCharArray())

        init {
            _decisionToDFA = arrayOfNulls(_ATN.numberOfDecisions)
            for (i in 0 until _ATN.numberOfDecisions) {
                _decisionToDFA[i] = DFA(_ATN.getDecisionState(i), i)
            }
        }
    }
}