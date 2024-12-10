package mill.main.client

import java.io.{InputStream, OutputStream}
import java.util.function.{BooleanSupplier, Supplier}

class InputPumper(
    src: Supplier[InputStream],
    dest: Supplier[OutputStream],
    checkAvailable: Boolean,
    runningCheck: BooleanSupplier = () => true
) extends Runnable {

  private var running = true

  def this(src: Supplier[InputStream], dest: Supplier[OutputStream], checkAvailable: Boolean) =
    this(src, dest, checkAvailable, () => true)

  override def run(): Unit = {
    try {
      val srcStream = src.get()
      val destStream = dest.get()

      val buffer = new Array[Byte](100)
      while (running) {
        if (!runningCheck.getAsBoolean) running = false
        else {
          val n =
            try {
              srcStream.read(buffer)
            } catch {
              case _: Exception => -1
            }
          if (n == -1) running = false
          else if (n == 0) Thread.sleep(1)
          else {
            try {
              destStream.write(buffer, 0, n)
              destStream.flush()
            } catch {
              case _: java.io.IOException => running = false
            }
          }
        }
      }
    } catch {
      case e: Exception => throw new RuntimeException(e)
    }
  }
}
