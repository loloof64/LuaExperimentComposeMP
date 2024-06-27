import LuaParser.*
import org.antlr.v4.runtime.tree.ParseTreeListener

/**
 * This interface defines a complete listener for a parse tree produced by
 * [LuaParser].
 */
interface LuaListener : ParseTreeListener {
    /**
     * Enter a parse tree produced by [LuaParser.start_].
     * @param ctx the parse tree
     */
    fun enterStart_(ctx: Start_Context?)

    /**
     * Exit a parse tree produced by [LuaParser.start_].
     * @param ctx the parse tree
     */
    fun exitStart_(ctx: Start_Context?)

    /**
     * Enter a parse tree produced by [LuaParser.chunk].
     * @param ctx the parse tree
     */
    fun enterChunk(ctx: ChunkContext?)

    /**
     * Exit a parse tree produced by [LuaParser.chunk].
     * @param ctx the parse tree
     */
    fun exitChunk(ctx: ChunkContext?)

    /**
     * Enter a parse tree produced by [LuaParser.block].
     * @param ctx the parse tree
     */
    fun enterBlock(ctx: LuaParser.BlockContext?)

    /**
     * Exit a parse tree produced by [LuaParser.block].
     * @param ctx the parse tree
     */
    fun exitBlock(ctx: LuaParser.BlockContext?)

    /**
     * Enter a parse tree produced by [LuaParser.stat].
     * @param ctx the parse tree
     */
    fun enterStat(ctx: StatContext?)

    /**
     * Exit a parse tree produced by [LuaParser.stat].
     * @param ctx the parse tree
     */
    fun exitStat(ctx: StatContext?)

    /**
     * Enter a parse tree produced by [LuaParser.assign].
     * @param ctx the parse tree
     */
    fun enterAssign(ctx: AssignContext?)

    /**
     * Exit a parse tree produced by [LuaParser.assign].
     * @param ctx the parse tree
     */
    fun exitAssign(ctx: AssignContext?)

    /**
     * Enter a parse tree produced by [LuaParser.ifstat].
     * @param ctx the parse tree
     */
    fun enterIfstat(ctx: IfstatContext?)

    /**
     * Exit a parse tree produced by [LuaParser.ifstat].
     * @param ctx the parse tree
     */
    fun exitIfstat(ctx: IfstatContext?)

    /**
     * Enter a parse tree produced by [LuaParser.namelist].
     * @param ctx the parse tree
     */
    fun enterNamelist(ctx: NamelistContext?)

    /**
     * Exit a parse tree produced by [LuaParser.namelist].
     * @param ctx the parse tree
     */
    fun exitNamelist(ctx: NamelistContext?)

    /**
     * Enter a parse tree produced by [LuaParser.explist].
     * @param ctx the parse tree
     */
    fun enterExplist(ctx: ExplistContext?)

    /**
     * Exit a parse tree produced by [LuaParser.explist].
     * @param ctx the parse tree
     */
    fun exitExplist(ctx: ExplistContext?)

    /**
     * Enter a parse tree produced by [LuaParser.exp].
     * @param ctx the parse tree
     */
    fun enterExp(ctx: ExpContext?)

    /**
     * Exit a parse tree produced by [LuaParser.exp].
     * @param ctx the parse tree
     */
    fun exitExp(ctx: ExpContext?)

    /**
     * Enter a parse tree produced by [LuaParser.prefixexp].
     * @param ctx the parse tree
     */
    fun enterPrefixexp(ctx: PrefixexpContext?)

    /**
     * Exit a parse tree produced by [LuaParser.prefixexp].
     * @param ctx the parse tree
     */
    fun exitPrefixexp(ctx: PrefixexpContext?)

    /**
     * Enter a parse tree produced by [LuaParser.number].
     * @param ctx the parse tree
     */
    fun enterNumber(ctx: NumberContext?)

    /**
     * Exit a parse tree produced by [LuaParser.number].
     * @param ctx the parse tree
     */
    fun exitNumber(ctx: NumberContext?)
}