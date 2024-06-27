import LuaParser.*
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.TerminalNode

/**
 * This class provides an empty implementation of [LuaListener],
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
open class LuaBaseListener : LuaListener {
    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterStart_(ctx: Start_Context?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitStart_(ctx: Start_Context?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterChunk(ctx: ChunkContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitChunk(ctx: ChunkContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterBlock(ctx: LuaParser.BlockContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitBlock(ctx: LuaParser.BlockContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterStat(ctx: StatContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitStat(ctx: StatContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAssign(ctx: AssignContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAssign(ctx: AssignContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIfstat(ctx: IfstatContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIfstat(ctx: IfstatContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNamelist(ctx: NamelistContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNamelist(ctx: NamelistContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterExplist(ctx: ExplistContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitExplist(ctx: ExplistContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterExp(ctx: ExpContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitExp(ctx: ExpContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterPrefixexp(ctx: PrefixexpContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitPrefixexp(ctx: PrefixexpContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNumber(ctx: NumberContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNumber(ctx: NumberContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterEveryRule(ctx: ParserRuleContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitEveryRule(ctx: ParserRuleContext?) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun visitTerminal(node: TerminalNode) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun visitErrorNode(node: ErrorNode) {}
}