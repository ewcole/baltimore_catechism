package baltimore_catechism;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

/**
 * 
 */
public class CatechismReader extends Catechism1BaseListener {

  def catechism
  def lesson
  def question
  def qq
  def aa

  Catechism1Parser parser;
  TokenStream tokens = parser.getTokenStream();
  
  /**
   * {@inheritDoc}
   *
   * <p>Remove the catechism data at the beginning of the file.</p>
   */
  @Override public void enterFile(Catechism1Parser.FileContext ctx) { 
    catechism = null;
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void exitFile(Catechism1Parser.FileContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>Create an empty catechism object.</p>
   */
  @Override public void enterCatechism(Catechism1Parser.CatechismContext ctx) {
    catechism = [lessons: []]
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void exitCatechism(Catechism1Parser.CatechismContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>start a new lesson.</p>
   */
  @Override public void enterLesson(Catechism1Parser.LessonContext ctx) { 
    lesson = [number: "${ctx.ordinal().text}", title: "${tokens.getText(ctx.title())}", questions: []]
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void exitLesson(Catechism1Parser.LessonContext ctx) { 
    catechism.lessons << lesson;
    lesson = null;
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void enterQuestion(Catechism1Parser.QuestionContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void exitQuestion(Catechism1Parser.QuestionContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void enterQNum(Catechism1Parser.QNumContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void exitQNum(Catechism1Parser.QNumContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void enterList(Catechism1Parser.ListContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void exitList(Catechism1Parser.ListContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void enterListItem(Catechism1Parser.ListItemContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void exitListItem(Catechism1Parser.ListItemContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void enterLnum(Catechism1Parser.LnumContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void exitLnum(Catechism1Parser.LnumContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void enterOrdinal(Catechism1Parser.OrdinalContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void exitOrdinal(Catechism1Parser.OrdinalContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void enterTitle(Catechism1Parser.TitleContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void exitTitle(Catechism1Parser.TitleContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void enterText(Catechism1Parser.TextContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void exitText(Catechism1Parser.TextContext ctx) { }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void enterEveryRule(ParserRuleContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void exitEveryRule(ParserRuleContext ctx) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void visitTerminal(TerminalNode node) { }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   */
  @Override public void visitErrorNode(ErrorNode node) { }

  public CatechismReader(Catechism1Parser parser) {
    this.parser = parser;
    this.tokens = parser.getTokenStream();
  }

  public static readCatechism(Reader r) {
    def input = new ANTLRInputStream(r);
    def lexer = new Catechism1Lexer(input);
    def tokens = new CommonTokenStream(lexer);
    def parser = new Catechism1Parser(tokens);
    def tree = parser.file()
    def walker = new ParseTreeWalker()
    def cr = new CatechismReader(parser)
    walker.walk(cr, tree)
    return cr.catechism
  }

  public static void main(String[] args) {
    def inputFile = new File(args[0]);
    assert inputFile.exists()
    def bc = CatechismReader.readCatechism(inputFile.newReader())
    println bc
  }
 
}