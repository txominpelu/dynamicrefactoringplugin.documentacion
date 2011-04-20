
def Date sumarDiasAFechaConFinSemana(Date date, int laboralDaysToAdd){
    def calendario = Calendar.getInstance()
    calendario.setTime(date)
    int day = calendario.get(Calendar.DAY_OF_WEEK)
    int daysToAdd = laboralDaysToAdd + ((int)((day + laboralDaysToAdd) / 7) * 2)
    if (day + laboralDaysToAdd % 7 > 5 ){
        daysToAdd = daysToAdd + 2
    }
    println daysToAdd
    println calendario.get(Calendar.DAY_OF_WEEK)
    calendario.add(Calendar.DATE, daysToAdd)
    println calendario.get(Calendar.DAY_OF_WEEK)
    return calendario.getTime()
}
     
date = new Date().parse("dd-MM-yyyy", "26-01-2011")

sumarDiasAFechaConFinSemana(date, 16) 

