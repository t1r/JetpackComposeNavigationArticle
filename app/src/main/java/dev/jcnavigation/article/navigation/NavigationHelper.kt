package dev.jcnavigation.article.navigation

internal object NavigationHelper {

    /**
     * Формирует строку функциональны архументов,
     * если есть вероятность, что в строке будут символы вроде /
     * стоит использовать Uri.encode() для конвертирования строки
     *
     * @param args исходная строка
     * @return возвращает строку с опциональными аргументами
     * в формате ?argumentKey1={argumentValue1}&argumentKey2={argumentValue2}
     */
    fun buildOptionalArgumentsString(
        vararg args: Pair<String, Any?>,
    ): String {
        if (args.isEmpty()) return ""
        return args
            .mapNotNull { item ->
                if (item.second == null) null
                else item
            }
            .foldIndexed(
                ""
            ) { index, acc, item ->
                val key = item.first
                val value = item.second
                acc + when (index) {
                    0 -> "?${key}=${value}"
                    else -> "&${key}=${value}"
                }
            }
    }
}