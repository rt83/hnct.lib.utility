package hnct.lib.utility

/**
 * @author tduccuong
 */
object StringUtilTest extends App {
	val input = "nem  nuong     hanoi"
	val search = "nem"
	
  StringUtil.wordCombiOf("nem nuong hanoi") foreach { x =>
		println("Distance between '"+search+"' and '"+x+"' = "+StringUtil.levenshtein(search, x))
	}
}