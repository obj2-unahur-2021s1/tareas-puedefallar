package ar.edu.unahur.obj2.tareas

import java.lang.Math.ceil

interface Tarea {
    val horasEstimadas : Double
    fun horasNecesarias() : Double
    fun nominaEmpleados() : MutableList<Empleado>
    fun costo() : Double
}

class TareaSimple(override val horasEstimadas: Double, val nominaEmpleados:MutableList<Empleado>, val costoInfra: Double
) : Tarea{

    override fun horasNecesarias() : Double = ceil(horasEstimadas / (nominaEmpleados.size - 1))

    override fun nominaEmpleados(): MutableList<Empleado> = nominaEmpleados

    override fun costo(): Double = nominaEmpleados.sumBy { it.sueldoPorTarea(this).toInt() } + costoInfra
}

