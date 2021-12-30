package main

type Mediator interface {
	canLand(Plane) bool
	notifyTakeOff()
}
