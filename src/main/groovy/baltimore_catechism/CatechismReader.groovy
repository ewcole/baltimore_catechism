package baltimore_catechism;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import groovy.json.*;
import groovy.xml.*;
/**
 * 
 */
public class CatechismReader extends Catechism1BaseListener {

  def catechism
  def lesson
  def question
  def qq
  def aa
  def list

  static int ordinal2int(String ord) {
    def ords = [FIRST:     1,
                SECOND:      2,
                THIRD:       3,
                FOURTH:      4,
                FIFTH:       5,
                SIXTH:       6,
                SEVENTH:     7,
                EIGHTH:      8,
                NINTH:       9,
                TENTH:       10,
                ELEVENTH:    11,
                TWELFTH:     12,
                THIRTEENTH:  13,
                FOURTEENTH:  14,
                FIFTEENTH:   15,
                SIXTEENTH:   16,
                SEVENTEENTH: 17,
                EIGHTEENTH:  18,
                NINTEENTH:   19,
                TWENTIETH:   20,
                TWENTY:      20,
                THIRTIETH:   30,
                THIRTY:      30]
    ord.toUpperCase().split('-').inject(0) {
      n, segment ->
        n += (ords[segment])?:0
        n
    }
  }

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
   * <p>Create an empty catechism object.</p>
   */
  @Override public void enterCatechism(Catechism1Parser.CatechismContext ctx) {
    catechism = [lessons: []]
  }

  /**
   * {@inheritDoc}
   *
   * <p>start a new lesson.</p>
   */
  @Override public void enterLesson(Catechism1Parser.LessonContext ctx) { 
    def ord = ctx.ordinal().text
    lesson = [number: ordinal2int(ord), ordinal: "${ord}", 
              title: "${tokens.getText(ctx.title())}", questions: []]
  }

  /**
   * {@inheritDoc}
   *
   * <p>Save the lesson on the end of the list of lessons for the catechism.</p>
   */
  @Override public void exitLesson(Catechism1Parser.LessonContext ctx) { 
    catechism.lessons << lesson;
    lesson = null;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Create a new, blank question.</p>
   */
  @Override public void enterQuestion(Catechism1Parser.QuestionContext ctx) { 
    question = [number:   ctx.qNum().text.replaceAll(/\.$/,''),
                question: "${tokens.getText(ctx.qText())}",
                answer:   "${tokens.getText(ctx.aText())}"]
  }
  /**
   * {@inheritDoc}
   *
   * <p>Save the current question.</p>
   */
  @Override public void exitQuestion(Catechism1Parser.QuestionContext ctx) { 
    lesson.questions << question
  }

  /** Create a new list answer for the current question.
   */
  @Override public void enterList(Catechism1Parser.ListContext ctx) {
    list = []
  }
  /**
   * {@inheritDoc}
   *
   * <p>Save the list answer to the current question.</p>
   */
  @Override public void exitList(Catechism1Parser.ListContext ctx) { 
    question.list = list
  }
  /**
   * {@inheritDoc}
   *
   * <p>Add a new item to the answer list for the current question.</p>
   */
  @Override public void enterListItem(Catechism1Parser.ListItemContext ctx) {
    // assert list instanceof ArrayList
    list.add([number: ctx.lnum().text.replaceAll(/\.$/,''), 
              text: tokens.getText(ctx.text())])
  }

  @Override public void visitErrorNode(ErrorNode node) { }

  /** Create a new CatechismReader object.  
   *  @param parser A Catechism1Parser.  It's needed because we are sending
   *                whitespace to the HIDDEN channel.
   */
  public CatechismReader(Catechism1Parser parser) {
    this.parser = parser;
    this.tokens = parser.getTokenStream();
  }

  /** Parse the input stream using the Catechism1 parser
   */
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

  public static String catechism2Json(def catechism) {
    def j = new JsonOutput()
    j.prettyPrint(j.toJson(catechism));
  }

  // public static String catechism2Xml(def catechism) {
  //   def s = new StringWriter();
  //   def x = new MarkupBuilder(s);
  //   x.baltimore_catechism {
  //     lessons {
  //       catechism?.lessons?.each {
  //         lesson ->
  //           lesson(number: it.number, title: it.title, ordinal: it.ordinal)
  //       }
  //     }
  //   }
  //   return s.toString()
  // }
  /** Read the input file and write the output file */
  public static void main(String[] args) {
    // def cli = new CliBuilder(usage: "parse the input file");
    // cli.j(longOpt: 'json_file', args: 1, "JSON output file name");
    // def opt = cli.parse(args);
    def inputFile = new File(args[0]);
    assert inputFile.exists()
    def bc = CatechismReader.readCatechism(inputFile.newReader())
    println bc
    new File('baltimore_catechism.json').text = CatechismReader.catechism2Json(bc)
    //new File('baltimore_catechism.xml').text = CatechismReader.catechism2Xml(bc)
  }
 
}