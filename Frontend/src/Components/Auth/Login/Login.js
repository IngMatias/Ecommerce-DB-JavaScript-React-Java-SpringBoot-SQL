import { useContext, useState } from "react"

import { DataContext } from "../../Data/DataContext/DataContext"

import { LoginOp } from "../../Data/Api/Api"

function Login() {
	const { state, setState } = useContext(DataContext)

	const [loginState, setLoginState] = useState({
		email: "",
		password: "",
	})

	const onChange = e => {
		setLoginState({
			...loginState,
			[e.target.name]: e.target.value,
		})
	}

	const onClick = e => {
		LoginOp(loginState)
			.then(user => {
				user.password = loginState.password
				setState({
					...state,
					logged: true,
					user,
				})
				console.log('LogedIn', state)
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
			<h1 className="form_title">Login</h1>
			<div className='centered vertical-flex'>
				<input className="form_field"
					name='email'
					type='text'
					placeholder='email'
					onChange={onChange}
				></input>
				<input className="form_field"
					name='password'
					type='password'
					placeholder='password'
					onChange={onChange}
				></input>
			</div>
			<div
				id='login-error-msg'
				className='login-error-msg-disappearing centered'
			></div>
			<div className='width-100 centered'>
				<button className="form_button" onClick={onClick}>LogIn</button>
			</div>
		</div>
	)
}

export { Login }
