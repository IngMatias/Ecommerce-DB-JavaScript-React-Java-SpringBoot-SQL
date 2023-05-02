import { PublicationsGallery } from "../../View/PublicationsGallery/PublicationsGallery"
import { Publication } from "../../Input/Publication/Publication"

function Home() {
	return (
		<div className='centered vertical-flex'>
			<PublicationsGallery />
			<Publication />
		</div>
	)
}

export { Home }
