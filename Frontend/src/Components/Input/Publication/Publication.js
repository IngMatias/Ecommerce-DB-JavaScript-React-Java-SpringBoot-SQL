import React, { useContext, useState } from "react"

import { DataContext } from "../../Data/DataContext/DataContext"

import { Sticker } from "../../Input/Sticker/Sticker"
import { Condition } from "../../Input/Condition/Condition"

import { createPublicationOp } from "../../Data/Api/Api"

function Publication() {
	const { state } = useContext(DataContext)

	const [publicationState, setPublicationState] = useState({
		title: "",
		description: "",
		date: "",
		photo: "",
		condition: "",
	})

	const [stickerState, setStickerState] = useState({
		country: "",
		number: "",
	})

	const onChangeText = e => {
		setPublicationState({
			...publicationState,
			[e.target.name]: e.target.value,
		})
	}

	const onChangePhoto = e => {
		console.log('parent photo')
		const file = e.target.files[0]
		const reader = new FileReader()
		reader.onload = event => {
			setPublicationState({
				...publicationState,
				[e.target.name]: event.target.result,
			})
		}
		reader.readAsDataURL(file)
	}

	const onClick = e => {
		createPublicationOp(state.user, publicationState, stickerState)
			.then(e => setErrorMsg("Created"))
			.catch(e => setErrorMsg("Error" + e.error))
	}

	const setErrorMsg = msg => {
		document.getElementById("publication-creator-error-msg").innerHTML = msg
	}

	const setSticker = (index, prop, value) => {
		setStickerState({
			...stickerState,
			[prop]: value,
		})
		console.log("Sticker setted", JSON.stringify(stickerState))
	}

	const getSticker = index => {
		return JSON.parse(JSON.stringify(stickerState))
	}

	return (
		<div className='container vertical-flex'>
			<h1>Add a publication</h1>
			<input
				className='form_field'
				name='title'
				type='text'
				placeholder='title'
				onChange={onChangeText}
			></input>

			<input
				className='form_field'
				name='description'
				type='text'
				placeholder='description'
				onChange={onChangeText}
			></input>

			{/* <input
				className='form_field'
				name='photo'
				type='file'
				onChange={onChangePhoto}
			></input> */}

			<Sticker
				data-index='0'
				sticker={{ getSticker, setSticker, onChangePhoto }}
			/>
			<Condition condition={[publicationState, setPublicationState]} />

			<div
				id='publication-creator-error-msg'
				className='publication-creator-error-msg-disappearing centered'
			></div>

			<div className='width-100 centered'>
				<button
					className='form_button'
					onClick={onClick}
				>
					Create
				</button>
			</div>
		</div>
	)
}

export { Publication }
