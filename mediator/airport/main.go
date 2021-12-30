package main

func main() {
	tower := NewAirportTower()
	boeing := NewBoeing(tower)
	jet := NewJet(tower)

	boeing.land()
	jet.land()
	boeing.takeOff()

}
