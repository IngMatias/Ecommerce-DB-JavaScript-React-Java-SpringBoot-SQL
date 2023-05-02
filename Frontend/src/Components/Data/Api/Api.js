const host = "http://localhost:8080"

async function fetchOp(url, method, body) {
	console.log("body", body)
	return new Promise((resolve, reject) => {
		fetch(host + url, {
			method: method,
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(body),
		})
			.then(res => {
				return res.json()
			})
			.then(data => {
				if (data.error) {
					throw data
				}
				resolve(data)
			})
			.catch(e => reject(e))
	})
}

function LoginOp({ email, password }) {
	const url = "/user/login"
	const method = "POST"
	const body = { email, password }
	return fetchOp(url, method, body)
}

function RegisterOp({ name, email, password, phone, ubication }) {
	const url = "/user/register"
	const method = "POST"
	const body = { name, email, password, phone, ubication }
	return fetchOp(url, method, body)
}

function getPublicationsByUserOp({ email, password }) {
	const url = "/user/publications"
	const method = "POST"
	const body = { email, password }
	return fetchOp(url, method, body)
}

function getPublicationsOp(page) {
	const url = "/publications"
	const method = "POST"
	const body = { page: page }
	return fetchOp(url, method, body)
}

function getCountriesOp() {
	const url = "/sticker/countries"
	const method = "GET"
	const body = undefined
	return fetchOp(url, method, body)
}

function getNumbersByCountryOp(country) {
	const url = "/stickers/numbers/" + country
	const method = "GET"
	const body = undefined
	return fetchOp(url, method, body)
}

function getConditionsOp() {
	const url = "/publications/conditions"
	const methdo = "GET"
	const body = undefined
	return fetchOp(url, methdo, body)
}

function createPublicationOp(
	{ email, password },
	{ title, description, date, photo, condition },
	{ country, number },
) {
	const url = "/publication"
	const method = "POST"
	const body = {
		user: { email, password },
		publication: { title, description, date, photo, condition },
		sticker: { country, number },
	}
	console.log(body.sticker)
	return fetchOp(url, method, body)
}

function addLooksForOp({ email, password }, { title }, { country, number }) {
	const url = "/looksFor"
	const method = "POST"
	const body = { user: { email, password }, publication: { title }, sticker: { country, number } }
	return fetchOp(url, method, body)
}

function getPublicationOp({ title }) {
	const url = "/publication/" + title
	const method = "GET"
	const body = undefined
	return fetchOp(url, method, body)
}

function createOfferOp({ email, password }, { title }, offer) {
	const url = "/offer"
	const method = "POST"
	const body = {
		user: { email, password },
		publication: { title },
		offer: offer,
	}
	return fetchOp(url, method, body)
}

function acceptOfferOp({ email, password }, { title }, { offerNewState }) {
	const url = "/offer/update"
	const method = "POST"
	const body = {
		user: { email, password },
		offer: { title },
		offerNewState: offerNewState,
	}
	return fetchOp(url, method, body)
}

export {
	LoginOp,
	RegisterOp,
	getPublicationsOp,
	getCountriesOp,
	getNumbersByCountryOp,
	getConditionsOp,
	createPublicationOp,
	addLooksForOp,
	getPublicationOp,
	createOfferOp,
	getPublicationsByUserOp,
	acceptOfferOp,
}
