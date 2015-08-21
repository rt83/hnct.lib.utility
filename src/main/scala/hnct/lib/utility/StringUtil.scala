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
  
  /**
   * Generate combinations of words of a string
   */
//  def wordCombiOf(s: String, separator: String = " "): Seq[String] = {
//    val words = s.trim.split(" ").map(_.trim).toList
//    var result = words.toVector
//    
//    def combi(prefix: String, ts: List[String]): Unit = ts match {
//      case Nil => ()
//      case h::tail =>
//        for (x <- ts) 
//          result = result :+ (prefix + separator + x) 
//        combi(prefix + separator + h, tail)
//    }
//    combi(words.head, words.tail)
//    
//    result
//  }
  def wordCombiOf(s: String, seperator: String = " "): Seq[String] = {
    val wordSet = s.split(seperator).map(_.trim).filter(!_.isEmpty()).toSet
    wordSet.subsets(3).filter(!_.isEmpty).map(_.mkString(seperator)).toSeq
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
  
}