<p align="center">
   <img src="assets/20230703_152909_2023-07-03_14-25.png">
</p>

# Conversor de monedas en tiempo real

_Este programa es el resultado del Challenge propuesto por [Alura Latam](https://www.aluracursos.com) para los alumnos de **Oracle Next Education G5**. El objetivo del desafío era crear un programa utilizando **Java** que permitiera convertir la moneda local del alumno a diferentes tipos de monedas de distintos países, y viceversa._

_Adicionalmente, se nos animó a agregar una funcionalidad adicional de conversión. En mi caso, he implementado la conversión de temperaturas. Siéntete libre de explorar el código y utilizarlo como referencia para tus propios proyectos. El proyecto cuenta con licencia GNU, lo cual lo hace libre para todos. :)_

## Comenzando 🚀

### Pre-requisitos 📋

```
- Java Development Kit (JDK) 17 o superior. https://www.java.com/es/download/
- Gson para la deserializacion de JSON. https://github.com/google/gson
- Conexcion a internet para obtener las tasas actualizadas (de lo contrario el programa realizara la conversion a 1.0 siempre).
- API key de ExchangeRate-API si la mia ya no funciona (opcional). https://www.exchangerate-api.com/docs/java-currency-api
```

## Características del programa ⚙️

_Aqui se muestran las funciones del programa:_

```
- Menu principal para seleccionar el tipo de conversion.
- Input para captar el valor a convertir.
- Conversion en 5 tipos de monedas distintas (Dolar, Euro, Libras Esterlinas, Yen Japones y Won Sur Coreano).
- Conversion de Celsius a Fahrenheit, y viceversa.
- Cuadro de dialogo del resultado de conversion.
- Cuadro de dialogo para continuar o finalizar el programa.
- Cuadro de dialogo de finalizacion del programa.
- Gestion de errores si se ingresa caracteres no numericos.
```

### Conversion de monedas

![](assets/20230703_160444_2023-07-03_15-02-45.gif)

### Conversion de temperatura

![](assets/20230703_160544_2023-07-03_15-03-56.gif)

## Creditos :star2:

* **Angel Pacheco** - *Desarrollo del proyecto* - [LinkedIn](https://www.linkedin.com/in/angel-sobri-pacheco/https://github.com/villanuevan) [GitHub](https://github.com/constSobri)
* **Google** - *Libreria Gson* - [Google](https://github.com/google/gson)
* **Oracle** - *Oportunidad de estudiar desarrollo gracias a ellos* - [Oracle](https://www.oracle.com)
* **Alura Latam** - *Aprendizaje necesario para la realizacion de este programa* - [Alura Latam](https://www.aluracursos.com)

## Licencia 📄

Este proyecto está licenciado bajo la [Licencia GNU General Public License v3.0](LICENSE.md). Puedes utilizar, modificar y distribuir este software de acuerdo con los términos de dicha licencia.

## Agradecimientos 🎁

* Gracias a [Villanuevand](https://github.com/Villanuevand) por el ejemplo de README.md.
* Y gracias a todos por ver este proyecto.
