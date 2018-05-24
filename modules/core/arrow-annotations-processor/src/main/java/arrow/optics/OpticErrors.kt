package arrow.optics

import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement

val Element.otherClassTypeErrorMessage
  get() = """
      |$this cannot be annotated with ${opticsAnnotationClass.canonicalName}
      | ^
      |
      |Only data and sealed classes can be annotated with ${opticsAnnotationClass.canonicalName} annotation""".trimMargin()

val Element.lensErrorMessage
  get() = """
      |Cannot generate $Lens for $this
      |                                       ^
      |arrow.optics.OpticsTarget.LENS is an invalid ${opticsAnnotationClass.canonicalName} argument for $this.
      |It is only valid for data classes.
      """.trimMargin()

val Element.optionalErrorMessage
  get() = """
      |Cannot generate $Optional for $this
      |                                           ^
      |arrow.optics.OpticsTarget.OPTIONAL is an invalid ${opticsAnnotationClass.canonicalName} argument for $this.
      |It is only valid for data classes.
      """.trimMargin()

val Element.prismErrorMessage
  get() = """
      |Cannot generate $Prism for $this
      |                                        ^
      |arrow.optics.OpticsTarget.PRISM is an invalid ${opticsAnnotationClass.canonicalName} argument for $this.
      |It is only valid for sealed classes.
      """.trimMargin()

val Element.isoErrorMessage
  get() = """
      |Cannot generate $Iso for $this
      |                                      ^
      |arrow.optics.OpticsTarget.ISO is an invalid ${opticsAnnotationClass.canonicalName} argument for $this.
      |It is only valid for data classes.
      """.trimMargin()

val Element.isoTooBigErrorMessage
  get() = """
      |Cannot generate $Iso for $this
      |                                      ^
      |Iso generation is supported for data classes with up to 22 constructor parameters.
      """.trimMargin()

val Element.dslErrorMessage
  get() = """
      |Cannot generate Optics DSL for $this
      |                                ^
      |arrow.optics.OpticsTarget.DSL is an invalid ${opticsAnnotationClass.canonicalName} argument for $this.
      |It is only valid for data classes and sealed classes or top level functions without parameters.
      """.trimMargin()

val ExecutableElement.dslFunctionParametersMessage
  get() = """
      |Cannot generate Optics DSL for $this
      |                                ^
      |Top level Function annotated with ${opticsAnnotationClass.canonicalName} cannot have any parameters.
      """.trimMargin()

private val validOptics = (Optic.values.map(Optic::toString) + POptic.values.map(POptic::toString)).map { it.replace("arrow.optics.", "") }.joinToString()

val ExecutableElement.dslWrongOptic
  get() = """
  |Cannot generate Optics DSL for $this
  |                                ^
  |Top level Function annotated with ${opticsAnnotationClass.canonicalName} must return monomorphic optic. Candidates are $validOptics.
  """.trimMargin()