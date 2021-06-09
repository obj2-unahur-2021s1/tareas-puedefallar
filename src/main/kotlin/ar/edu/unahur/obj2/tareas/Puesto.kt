package ar.edu.unahur.obj2.tareas


interface Puesto {
    abstract fun sueldoPorTarea(tarea:Tarea, empleado: Empleado) : Double
}

object EmpleadoComun:Puesto {
    override fun sueldoPorTarea(tarea:Tarea, empleado: Empleado): Double = empleado.sueldoPorHora * tarea.horasNecesarias()

}
object Responsable:Puesto {
    override fun sueldoPorTarea(tarea:Tarea, empleado: Empleado): Double = empleado.sueldoPorHora * tarea.horasEstimadas
}

class Empleado(val sueldoPorHora: Int, val puesto: Puesto) {
    fun sueldoPorTarea(tarea: Tarea) = puesto.sueldoPorTarea(tarea, this)
}

