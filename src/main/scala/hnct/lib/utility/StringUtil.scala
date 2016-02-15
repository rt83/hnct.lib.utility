package hnct.lib.utility

/**
 * @author tduccuong
 */
object StringUtil {
  
  /**
   * Compute Levenshtein distance between two strings
   */
  def levenshtein(str1: String, str2: String): Int = {
    def min(nums: Int*): Int = nums.min
    
    val lenStr1 = str1.length
    val lenStr2 = str2.length

    val d: Array[Array[Int]] = Array.ofDim(lenStr1 + 1, lenStr2 + 1)

    for (i <- 0 to lenStr1) d(i)(0) = i
    for (j <- 0 to lenStr2) d(0)(j) = j

    for (i <- 1 to lenStr1; j <- 1 to lenStr2) {
      val cost = if (str1(i-1) == str2(j-1)) 0 else 1

      d(i)(j) = min(
        d(i-1)(j) + 1,     // deletion
        d(i)(j-1) + 1,     // insertion
        d(i-1)(j-1) + cost   // substitution
      )
    }

    d(lenStr1)(lenStr2)
  }
  
  def wordCombiOf(s: String, seperator: String = " "): Seq[String] = {
    val wordSet = s.split(seperator).map(_.trim).filter(!_.isEmpty()).toSet
    wordSet.subsets.filter(!_.isEmpty).map(_.mkString(seperator)).toSeq
  }
  
  def wordCombiOf(s: String, len: Int, seperator: String): Seq[String] = {
    val wordSet = s.split(seperator).map(_.trim).filter(!_.isEmpty()).toSet
    wordSet.subsets(len).filter(!_.isEmpty).map(_.mkString(seperator)).toSeq
  }
  
  def wordPermuOf(phrase: String, separator: String,  len: Int = 0) = {
    def generate(base: String, rest: List[String], result: Vector[String]): Vector[String] = rest match {
      case Nil =>
        result
      case head::tail =>
        val partial = rest.foldLeft(Vector[String]()){(acc, word) => acc :+ (base + separator + word)}
        generate(base + separator + head, tail, result ++ partial)
    }
    
    def accumulate(words: List[String], acc: Vector[String]): Vector[String] = {
      words match {
        case Nil => acc
        case head::tail =>
          val permus = generate(head, tail, Vector[String]())
          val partial = acc ++ permus
          val partialFiltered = if (len > 0)
            partial.filter(_.split(separator).length == len)
          else
            partial
          accumulate(tail, partialFiltered)
      }
    }
    
    val words = phrase.split(separator).toList
    accumulate(words, words.toVector)
  }
  
  /**
   * Strip unwanted characters out of the given string
   */
  def stripAll(s: String, unwanteds: String): String = {
    @scala.annotation.tailrec def start(n: Int): String = 
        if (n == s.length) ""
        else if (unwanteds.indexOf(s.charAt(n)) < 0) end(n, s.length)
        else start(1 + n)

    @scala.annotation.tailrec def end(a: Int, n: Int): String =
        if (n <= a) s.substring(a, n)
        else if (unwanteds.indexOf(s.charAt(n - 1)) < 0) s.substring(a, n)
        else end(a, n - 1)

   	start(0)
	}
  
  def trimAll(s: String, unwanteds: String): String =
  	s.toList.filter { x => !unwanteds.contains(x) }.mkString
  	
  /**
   * Given a word W1 and a phrase, phrase fuzzy-contains W1 
   * if exists a word W2 in the phrase such that levenshtein(W1, W2) <= lvsh.
   */
  def fuzzyContains(word: String, phrase: String, lvsh: Int = 1): Boolean = {
    val len = word.trim.split(" ").size
    val pLen = phrase.trim.split(" ").size
    if (len > pLen) false
    else {
      val _word = word.toLowerCase
      val permus = wordPermuOf(phrase.toLowerCase, " ", len)
      permus.filter{permu =>
        levenshtein(_word, permu) <= lvsh
      }.headOption match {
        case None => false
        case Some(w) => true
      }
    }
  }
  
}