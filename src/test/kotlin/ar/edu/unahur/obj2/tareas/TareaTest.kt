package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class TareaTest : DescribeSpec({
  //Empleados varios
  val responsable1 = Empleado(1500, Responsable)
  val responsable2 = Empleado(1200, Responsable)
  val responsable3 = Empleado(1000, Responsable)
  val responsable4 = Empleado(980, Responsable)
  val desarrollador = Empleado(500, EmpleadoComun)
  val analista = Empleado(700, EmpleadoComun)
  val diseniador = Empleado(500, EmpleadoComun)
  //Tareas
  val tareaFacil = TareaSimple(200.0, mutableListOf(responsable1, desarrollador), 20000.0)
  val tareaSencilla = TareaSimple(150.0, mutableListOf(responsable2, diseniador), 10000.0)
  val tareaSimple = TareaSimple(50.0, mutableListOf(responsable3, analista), 5000.0)

  val tareaCompleja = TareaIntegracion(responsable4,300.0)
  tareaCompleja.subtareas = mutableListOf<Tarea>(tareaFacil,tareaSencilla,tareaSimple)

  describe("Tareas Simple") {

    it("nomina de empleados, horas necesarias para tareaFacil") {
      tareaFacil.nominaEmpleados().shouldContainExactly(responsable1, desarrollador)
      tareaFacil.horasNecesarias().shouldBe(200.0)
      tareaFacil.costo().shouldBe(420000.0)
    }

    it("nomina de empleados, horas necesarias para tareaSencilla") {
      tareaSencilla.nominaEmpleados().shouldContainExactly(responsable2, diseniador)
      tareaSencilla.horasNecesarias().shouldBe(150.0)
      tareaSencilla.costo().shouldBe(265000)
    }
    it("nomina de empleados, horas necesarias para la tarea y costo") {
    tareaSimple.nominaEmpleados().shouldContainExactly(responsable3, analista)
    tareaSimple.horasNecesarias().shouldBe(50.0)
    tareaSimple.costo().shouldBe(90000.0)
  }
}

  describe ( "Una tarea de integracion"){

    it ("nomina de empleados, horas necesarias para la tarea y costo"){

      tareaCompleja.nominaEmpleados().shouldContainExactly(responsable1, desarrollador, responsable2, diseniador
             ,responsable3,analista,responsable4)

      tareaCompleja.horasNecesarias().shouldBe(437.5)
      tareaCompleja.costo().shouldBe(798250.0)

    }
  }

  describe("Tarea de integracion anidada") {
    val tareaComplejisima = TareaIntegracion(responsable2, 200.0)
    tareaComplejisima.subtareas = mutableListOf<Tarea>(tareaFacil, tareaCompleja)

    it("nomina de empleados, horas necesarias para la tarea y costo") {
      tareaComplejisima.subtareas.shouldContainExactly(tareaFacil, tareaCompleja)

      tareaComplejisima.nominaEmpleados().shouldContainExactly(responsable1, desarrollador,responsable1, desarrollador, responsable2, diseniador
      ,responsable3,analista,responsable4, responsable2)

      tareaComplejisima.horasNecesarias().shouldBe(662.5)
      tareaComplejisima.costo().shouldBe(1254797.5)

    }
  }
})
