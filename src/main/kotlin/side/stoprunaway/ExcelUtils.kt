package side.stoprunaway

import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.web.multipart.MultipartFile

class ExcelUtils {

    companion object {
        fun readFirstColumn(file: MultipartFile): List<String> {
            val values = mutableListOf<String>()
            file.inputStream.use { inputStream ->
                WorkbookFactory.create(inputStream).use { workbook ->
                    val sheet = workbook.getSheetAt(0)
                    for (row in sheet) {
                        val cell = row.getCell(0)
                        cell?.let {
                            values.add(it.toString())
                        }
                    }
                }
            }
            return values
        }
    }
}