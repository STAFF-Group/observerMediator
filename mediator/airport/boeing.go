package main

import "fmt"

type Boeing struct {
	mediator Mediator
}

func NewBoeing(newMediator Mediator) *Boeing {
	return &Boeing{
		mediator: newMediator,
	}
}

func (b *Boeing) takeOff() {
	fmt.Println("Boeing taking off...")
	b.mediator.notifyTakeOff()
}

func (b *Boeing) land() {
	if b.mediator.canLand(b) {
		fmt.Println("Boeing landing...")
		return
	}
	fmt.Println("Boeing landing delayed: waiting...")
}
