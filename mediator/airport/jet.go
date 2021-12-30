package main

import "fmt"

type Jet struct {
	mediator Mediator
}

func NewJet(newMediator Mediator) *Jet {
	return &Jet{
		mediator: newMediator,
	}
}

func (j *Jet) takeOff() {
	fmt.Println("Jet taking off...")
	j.mediator.notifyTakeOff()
}

func (j *Jet) land() {
	if j.mediator.canLand(j) {
		fmt.Println("Jet landing...")
		return
	}
	fmt.Println("Jet landing delayed: waiting...")
}
