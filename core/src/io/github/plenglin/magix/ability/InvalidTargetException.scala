package io.github.plenglin.magix.ability

import io.github.plenglin.magix.Targetable

class InvalidTargetException(val target: Option[Targetable]) extends Exception(s"Invalid target $target") {

}
