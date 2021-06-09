package ar.edu.unahur.obj2.tareas

import java.lang.Math.ceil
import java.nio.DoubleBuffer

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

    override fun costo(): Double = nominaEmpleados.sumByDouble { it.sueldoPorTarea(this) } + costoInfra
}

class TareaIntegracion(val responsable:Empleado, override val horasEstimadas: Double) : Tarea{
    var subtareas = mutableListOf<Tarea>()
    val duracionReuniones = horasEstimadas / 8

    override fun horasNecesarias(): Double {
        return (subtareas.sumByDouble { it.horasNecesarias() } + duracionReuniones)
    }

    override fun costo(): Double {
        return (subtareas.sumByDouble { it.costo()} + (subtareas.sumByDouble { it.costo()} * 0.03))
    }


    override fun nominaEmpleados(): MutableList<Empleado> {
        val nomina = subtareas.flatMap { it.nominaEmpleados() }.toMutableList()
        nomina.add(responsable)
        return nomina

    }

}

