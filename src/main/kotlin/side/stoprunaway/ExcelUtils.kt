package side.stoprunaway

import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.web.multipart.MultipartFile

class ExcelUtils {

    companion object {
        fun readFirstColumn(file: MultipartFile): List<Model.Word> {
            val ret = mutableListOf<Model.Word>()
            file.inputStream.use { inputStream ->
                WorkbookFactory.create(inputStream).use { workbook ->
                    val sheet = workbook.getSheetAt(0)
                    for (row in sheet) {
                        val firstCell = row.getCell(0)
                        val secondCell = row.getCell(1)
                        if (firstCell != null && secondCell != null) {
                            ret.add(Model.Word(
                                firstCell.toString(),
                                secondCell.toString()
                            ))
                        }
                    }
                }
            }
            return ret
        }
    }
}