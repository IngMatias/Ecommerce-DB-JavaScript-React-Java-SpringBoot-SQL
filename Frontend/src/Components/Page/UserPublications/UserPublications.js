import React, { useContext, useState, useEffect } from "react"
import { DataContext } from "../../Data/DataContext/DataContext"

import { getPublicationsByUserOp } from "../../Data/Api/Api"
import { Publication } from "../../View/Publication/Publication"

function UserPublications() {
	const [publicationState, setPublicationsState] = useState([])

	const { state, setState } = useContext(DataContext)

	useEffect(() => {
		if (state.user) {
			getPublicationsByUserOp(state.user)
				.then(pubs => {
					console.log(JSON.stringify(pubs))
					setPublicationsState(pubs)
				})
				.catch(e => console.log("Error", e.error))
		}
	}, [])
	if (!state.user) {
		return <div className="container">Non Logged User</div>
	}
	return (
		<div className='container publications-container'>
				{publicationState.map(pub => (
					<Publication
						key={pub.title}
						publication={pub}
					></Publication>
				))}
			</div>
		)

}

export { UserPublications }
