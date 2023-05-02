import React, { useContext, useState } from "react"

import { DataContext } from "../../Data/DataContext/DataContext"

import { RegisterOp } from "../../Data/Api/Api"

function Register() {
	const { state, setState } = useContext(DataContext)

	const [registerState, setRegisterState] = useState({
		name: "",
		email: "",
		password: "",
		phone: "",
		ubications: "",
	})

	const onChange = e => {
		setRegisterState({
			...registerState,
			[e.target.name]: e.target.value,
		})
	}

	const onClick = e => {
		RegisterOp(registerState)
			.then(user => {
				setState({
					...state,
					logged: true,
					user,
				})
				setErrorMsg("")
			})
			.catch(e => {
				setErrorMsg(e.error)
			})
	}

	const setErrorMsg = msg => {
		document.getElementById("login-error-msg").innerHTML = msg
	}

	if (state.logged) {
		return <div className="container">Logged</div>
	}
	return (
		<div className='container vertical-flex'>
			<h1 className='form_title'>Register</h1>
			<div className='centered vertical-flex'>
				<input
					className='form_field'
					name='name'
					type='text'
					placeholder='name'
					onChange={onChange}
				></input>
				<input
					className='form_field'
					name='email'
					type='text'
					placeholder='email'
					onChange={onChange}
				></input>
				<input
					className='form_field'
					name='password'
					type='password'
					placeholder='password'
					onChange={onChange}
				></input>
				<input
					className='form_field'
					name='phone'
					type='text'
					placeholder='phone'
					onChange={onChange}
				></input>
				<input
					className='form_field'
					name='ubication'
					type='text'
					placeholder='ubication'
					onChange={onChange}
				></input>
			</div>
			<div
				id='login-error-msg'
				className='login-error-msg-disappearing centered'
			></div>
			<div className='width-100 centered'>
				<button
					className='form_button'
					onClick={onClick}
				>
					Register
				</button>
			</div>
		</div>
	)
}

export { Register }
