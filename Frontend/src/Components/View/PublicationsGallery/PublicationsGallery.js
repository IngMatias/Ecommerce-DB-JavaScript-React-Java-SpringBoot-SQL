import { useEffect, useState } from "react"

import { getPublicationsOp } from "../../Data/Api/Api"

import { Publication } from "../Publication/Publication"

import "./PublicationsGallery.css"

function PublicationsGallery() {
	const [publicationState, setPublicationsState] = useState([])

	useEffect(() => {
		getPublicationsOp(1)
			.then(pubs => {
				setPublicationsState(pubs)
			})
			.catch(e => console.log("Error", e.error))
    }, [])
    
    

	return (
		<div className='container'>
			<h1>Publications</h1>
			<div className='publications-container'>
				{publicationState.map(pub => (
					<Publication
						key={pub.title}
						publication={pub}
					></Publication>
				))}
			</div>
		</div>
	)
}

export { PublicationsGallery }
