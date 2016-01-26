package hnct.lib.utility

/**
 * @author tduccuong
 */
object StringUtilTest extends App {
//	val input = "nem  nuong  ? + &&  hanoi"
//	val search = "nem"
//	println(StringUtil.trimAll(input, "?+&"))
//  StringUtil.wordCombiOf("nem nuong hanoi") foreach { x =>
//		println("Distance between '"+search+"' and '"+x+"' = "+StringUtil.levenshtein(search, x))
//	}
  
  val permus = StringUtil.wordCombiOf("cha ca").toVector
  println("combis: "+permus)
}