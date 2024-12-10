package mill.main.client

import java.io.FileDescriptor
import java.io.InputStreamReader
import java.io.FileInputStream
import java.io.File
import java.io.FileDescriptor

class FileReader(fd: FileDescriptor) extends InputStreamReader(new FileInputStream(fd)) {

  def this(file: File) = this(FileDescriptor.openReadOnly(file))
  def this(fileName: String) = this(new File(fileName))

}
