package side.stoprunaway.common

import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.util.StringUtil
import org.springframework.web.multipart.MultipartFile

class ExcelUtils {

    companion object {
        fun readFirstColumn(file: MultipartFile, weekNumber: Int): List<Model.ExcelWord> {
            val ret = mutableListOf<Model.ExcelWord>()
            file.inputStream.use { inputStream ->
                WorkbookFactory.create(inputStream).use { workbook ->
                    val sheet = workbook.getSheet("${weekNumber}주차") ?: return ret
                    for (row in sheet) {
                        val firstCell = row.getCell(0)
                        val secondCell = row.getCell(1)
                        if (StringUtil.isNotBlank(firstCell.toString()) && StringUtil.isNotBlank(secondCell.toString())) {
                            ret.add(
                                Model.ExcelWord(
                                    firstCell.toString(),
                                    secondCell.toString(),
                                )
                            )
                        }
                    }
                }
            }
            return ret
        }
    }
}