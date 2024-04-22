package ex1

import ex1.*
import org.scalatest.matchers.should.Matchers.*
import Parsers.*

class ParserScalaTests extends org.scalatest.flatspec.AnyFlatSpec:
  def parser = new BasicParser(Set('a', 'b', 'c'))
  def parserNE = new NonEmptyParser(Set('0', '1'))
  def parserNTC = new NotTwoConsecutiveParser(Set('X', 'Y', 'Z'))
  def parserNTCNE = new BasicParser(Set('X', 'Y', 'Z')) with NotTwoConsecutive[Char] with NonEmpty[Char]
  def sparser: Parser[Char] = "abc".charParser()
  def parserSTN = new ShortenThanNParser(Set('a', 'b', 'c'))(3)

  "BasicParser" should "parse correctly" in:
    parser.parseAll("aabc".toList) should be (true)
    parser.parseAll("aabcdc".toList) should be (false)
    parser.parseAll("".toList) should be (true)

  "NotEmptyParser" should "parse correctly" in:
    parserNE.parseAll("0101".toList) should be (true)
    parserNE.parseAll("0123".toList) should be (false)
    parserNE.parseAll(List()) should be (false)

  "NotTwoConsecutiveParser" should "parse correctly" in:
    parserNTC.parseAll("XYZ".toList) should be (true)
    parserNTC.parseAll("XYYZ".toList) should be (false)
    parserNTC.parseAll("".toList) should be (true)

  "NotEmptyAndNotTwoConsecutiveParser" should "parse correctly" in:
    parserNTCNE.parseAll("XYZ".toList) should be (true)
    parserNTCNE.parseAll("XYYZ".toList) should be (false)
    parserNTCNE.parseAll("".toList) should be (false)

  "StringParser" should "parse correctly" in:
    sparser.parseAll("aabc".toList) should be (true)
    sparser.parseAll("aabcdc".toList) should be (false)
    sparser.parseAll("".toList) should be (true)

  "ShortenThanNParser" should "parse correctly" in:
    parserSTN.parseAll("abc".toList) should be (true)
    parserSTN.parseAll("aabcdc".toList) should be (false)
    parserSTN.parseAll("".toList) should be (true)