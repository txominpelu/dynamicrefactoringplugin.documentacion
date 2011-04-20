inputFilePath = "planificacion-definitiva-proyecto.gan"
outputFilePath = "planificacion-definitiva-proyecto.csv"

def ficheroSalida = new File(outputFilePath)
ficheroSalida.delete()
ficheroSalida << "Tarea; Duración;  Fecha Inicio; Fecha Fin\n" 
def root = new XmlParser().parseText(new File(inputFilePath).text)
root.depthFirst().findAll { it.name() == 'task' } 
        .collect { new Task(name: it.@name, startDate: new Date().parse("yyyy-MM-dd",it.@start), duration: it.@duration) }.each { ficheroSalida << it.toCsvString() }


class Task {
    def name = ""
    def startDate = ""
    def duration = 0
    
    def String toCsvString(){
       def endDate = calculateEndDate(startDate, Integer.valueOf(duration)) 
       def end = endDate.format("dd/MM/yyyy")
       def start = startDate.format("dd/MM/yyyy")
       return "$name;$duration días;$start;$end\n"
    }
    
    public static Date calculateEndDate(Date startDate, int duration)
    {    
        Calendar startCal = Calendar.getInstance();
    
        startCal.setTime(startDate);
        
        for (int i = 1; i < duration; i++)
        {
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            while (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            {
                startCal.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
        startCal.add(Calendar.DAY_OF_MONTH, 1);
        return startCal.getTime();
    }
    
    def String toString (){
        def endDate = calculateEndDate(startDate, Integer.valueOf(duration)) 
        def end = endDate.format("dd/MM/yyyy")
        def start = startDate.format("dd/MM/yyyy")
        return "Tarea:$name - From:$start To:$end Duration:$duration días\n"
    }
}


