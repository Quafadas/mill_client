// package mill.main.client

// import java.io.{BufferedReader, File, FileReader, FileNotFoundException, IOException, PrintStream}
// import scala.util.{Failure, Success, Try}
// import scala.io.Source
// import java.nio.file.Paths
// import java.nio.file.Files
// import scala.concurrent.Future
// import scala.concurrent.ExecutionContext.Implicits.global

// object FileToStreamTailer {

//   def apply(
//       file: File,
//       outputStream: PrintStream,
//       pollingInterval: Long
//   ): Future[Unit] = {

//     var keepReading = true

//     if (file.exists()) {
//       var reader: BufferedReader = null
//       try {
//         val source = Source.fromFile(file)
//         var ignoreHead = true

//         reader = source.bufferedReader()
//         var line: String = null
//         println("old lines")
//         while ({ line = reader.readLine(); line != null }) {

//           println(line)
//         }

//         Future {
//           while ({ line = reader.readLine(); line != null }) {
//             println("new line" + line)
//             outputStream.println(line)
//           }
//           Sleep before checking for updates again
//           Thread.sleep(pollingInterval)
//         }

//       } finally {
//         if (reader != null) {
//           reader.close()
//         }
//         Future(())
//       }
//     } else {
//       Thread.sleep(pollingInterval)
//       apply(file, outputStream, pollingInterval)
//     }
//   }

//   private def readNewContent(file: File, previousKnownSize: Long): String = {

//     try {
//       // Skip lines that have already been processed
//       val content =
//         source.getLines(). .dropWhile(_.length < previousKnownSize).mkString("\n")
//       content
//     } finally {
//       source.close()
//     }
//   }
// }
