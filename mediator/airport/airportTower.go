package main

type airportTower struct {
	isRunwayClear bool
	planeQueue    []Plane
}

func NewAirportTower() *airportTower {
	return &airportTower{
		isRunwayClear: true,
	}
}

func (a *airportTower) canLand(p Plane) bool {
	if a.isRunwayClear {
		a.isRunwayClear = false
		return true
	}
	a.planeQueue = append(a.planeQueue, p)
	return false
}

func (a *airportTower) notifyTakeOff() {
	if !a.isRunwayClear {
		a.isRunwayClear = true
	}
	if len(a.planeQueue) > 0 {
		firstPlane := a.planeQueue[0]
		a.planeQueue = a.planeQueue[1:]
		firstPlane.land()
	}
}
