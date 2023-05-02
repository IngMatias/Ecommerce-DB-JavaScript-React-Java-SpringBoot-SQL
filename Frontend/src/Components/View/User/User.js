import { useContext } from "react"
import { DataContext } from "../../Data/DataContext/DataContext"

function User() {
	const { state } = useContext(DataContext)

	if (state.user) {
		return (
			<div className="container">
				<div>Name: {state.user.name}</div>
				<div>Email: {state.user.email}</div>
				<div>Ubication: {state.user.ubication}</div>
				<div>Phone: {state.user.phone}</div>
			</div>
		)
	}
}

export { User }
