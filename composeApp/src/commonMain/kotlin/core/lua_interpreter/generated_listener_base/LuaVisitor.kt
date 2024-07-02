import LuaParser.*
import org.antlr.v4.runtime.tree.ParseTreeVisitor

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by [LuaParser].
 *
 * @param <T> The return type of the visit operation. Use [Void] for
 * operations with no return type.
</T> */
interface LuaVisitor<T> : ParseTreeVisitor<T> {
    /**
     * Visit a parse tree produced by [LuaParser.start_].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitStart_(ctx: Start_Context?): T

    /**
     * Visit a parse tree produced by [LuaParser.chunk].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitChunk(ctx: ChunkContext?): T

    /**
     * Visit a parse tree produced by [LuaParser.block].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitBlock(ctx: LuaParser.BlockContext?): T

    /**
     * Visit a parse tree produced by the `semiColumnExec`
     * labeled alternative in [LuaParser.stat].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitSemiColumnExec(ctx: SemiColumnExecContext?): T

    /**
     * Visit a parse tree produced by the `assignExec`
     * labeled alternative in [LuaParser.stat].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitAssignExec(ctx: AssignExecContext?): T

    /**
     * Visit a parse tree produced by the `ifExec`
     * labeled alternative in [LuaParser.stat].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitIfExec(ctx: IfExecContext?): T

    /**
     * Visit a parse tree produced by [LuaParser.assign].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitAssign(ctx: AssignContext?): T

    /**
     * Visit a parse tree produced by [LuaParser.ifstat].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitIfstat(ctx: IfstatContext?): T

    /**
     * Visit a parse tree produced by [LuaParser.namelist].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitNamelist(ctx: NamelistContext?): T

    /**
     * Visit a parse tree produced by [LuaParser.explist].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitExplist(ctx: ExplistContext?): T

    /**
     * Visit a parse tree produced by the `prefixExpr`
     * labeled alternative in [LuaParser.exp].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitPrefixExpr(ctx: PrefixExprContext?): T

    /**
     * Visit a parse tree produced by the `unaryExpr`
     * labeled alternative in [LuaParser.exp].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitUnaryExpr(ctx: UnaryExprContext?): T

    /**
     * Visit a parse tree produced by the `exponentExpr`
     * labeled alternative in [LuaParser.exp].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitExponentExpr(ctx: ExponentExprContext?): T

    /**
     * Visit a parse tree produced by the `trueExpr`
     * labeled alternative in [LuaParser.exp].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitTrueExpr(ctx: TrueExprContext?): T

    /**
     * Visit a parse tree produced by the `numberExpr`
     * labeled alternative in [LuaParser.exp].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitNumberExpr(ctx: NumberExprContext?): T

    /**
     * Visit a parse tree produced by the `plusMinusExpr`
     * labeled alternative in [LuaParser.exp].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitPlusMinusExpr(ctx: PlusMinusExprContext?): T

    /**
     * Visit a parse tree produced by the `booleanAndExpr`
     * labeled alternative in [LuaParser.exp].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitBooleanAndExpr(ctx: BooleanAndExprContext?): T

    /**
     * Visit a parse tree produced by the `booleanOrExpr`
     * labeled alternative in [LuaParser.exp].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitBooleanOrExpr(ctx: BooleanOrExprContext?): T

    /**
     * Visit a parse tree produced by the `mulDivModuloExpr`
     * labeled alternative in [LuaParser.exp].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitMulDivModuloExpr(ctx: MulDivModuloExprContext?): T

    /**
     * Visit a parse tree produced by the `intBinaryLogicalExpr`
     * labeled alternative in [LuaParser.exp].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitIntBinaryLogicalExpr(ctx: IntBinaryLogicalExprContext?): T

    /**
     * Visit a parse tree produced by the `falseExpr`
     * labeled alternative in [LuaParser.exp].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitFalseExpr(ctx: FalseExprContext?): T

    /**
     * Visit a parse tree produced by the `booleanBinaryLogicalExpr`
     * labeled alternative in [LuaParser.exp].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitBooleanBinaryLogicalExpr(ctx: BooleanBinaryLogicalExprContext?): T

    /**
     * Visit a parse tree produced by the `variablePrefix`
     * labeled alternative in [LuaParser.prefix].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitVariablePrefix(ctx: VariablePrefixContext?): T

    /**
     * Visit a parse tree produced by the `parenthesisPrefix`
     * labeled alternative in [LuaParser.prefix].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitParenthesisPrefix(ctx: ParenthesisPrefixContext?): T

    /**
     * Visit a parse tree produced by the `integerValue`
     * labeled alternative in [LuaParser.number].
     * @param ctx the parse tree
     * @return the visitor result
     */
    fun visitIntegerValue(ctx: IntegerValueContext?): T
}