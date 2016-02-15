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
  
//  val permus = StringUtil.wordCombiOf("thit bo suon xao chua ngot theo phong cach ha noi").toVector
//  println("combis: "+permus)
  
//  StringUtil.wordPermuOf("la vong Cha ca ha noi", " ", 2).foreach(println)
  
  println(StringUtil.fuzzyContains("la vong Cha ca ha noi", "com"))
}