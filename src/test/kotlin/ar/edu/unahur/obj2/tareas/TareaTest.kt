package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class TareaTest : DescribeSpec({
  //Empleados varios
  val responsable1 = Empleado(1500, Responsable)
  val desarrollador = Empleado(500, EmpleadoComun)
  val analista = Empleado(700, EmpleadoComun)
  val diseniador = Empleado(500, EmpleadoComun)

  describe("Una tarea Simple") {
    val tareaFacil = TareaSimple(200.0, mutableListOf(responsable1, desarrollador, analista, diseniador), 20000.0)

    it("nomina de empleados, horas necesarias para la tarea y costo") {
      tareaFacil.nominaEmpleados().shouldContainExactly(responsable1, desarrollador, analista, diseniador)
      tareaFacil.horasNecesarias().shouldBe(67.0)
      tareaFacil.costo().shouldBe(433900.0)
    }
  }
})
