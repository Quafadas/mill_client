import utest._
import java.io.File
import java.nio.file.Paths
import scala.jdk.CollectionConverters._
import mill.main.client.Util

object MillEnvTests extends TestSuite {

  val tests = Tests {
    test("readOptsFileLinesWithoutFinalNewline") {
      val file = Paths.get(getClass.getClassLoader.getResource("file-wo-final-newline.txt").toURI).toFile
      val lines = Util.readOptsFileLines(file).asScala.toList
      val expectedLines = List(
        "-DPROPERTY_PROPERLY_SET_VIA_JVM_OPTS=value-from-file",
        "-Xss120m"
      )
      assert(lines == expectedLines)
    }
  }
}
