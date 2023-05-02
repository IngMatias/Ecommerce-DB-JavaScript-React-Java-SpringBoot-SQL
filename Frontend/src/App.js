import React from "react"
import { DataProvider } from "./Components/Data/DataContext/DataContext"
import { UserPublications } from "./Components/Page/UserPublications/UserPublications"
import { Publication } from "./Components/Page/Publication/Publication"
import { Home } from "./Components/Page/Home/Home"
import { User } from "./Components/View/User/User"
import { Register } from "./Components/Auth/Register/Register"
import { Login } from "./Components/Auth/Login/Login"
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom"

function App() {
	return (
		<DataProvider>
			<Router>
				<div className='nav'>
					<div className='nav-link-container'>
						<Link
							className='nav-link'
							to={"/"}
						>
							Home
						</Link>
					</div>

					<div className='nav-link-container'>
						<Link
							className='nav-link'
							to={"/myPublications"}
						>
							My Publications
						</Link>
					</div>

					<div className='nav-link-container'>
						<Link
							className='nav-link'
							to={"/user"}
						>
							User
						</Link>
					</div>

					<div className='nav-link-container'>
						<Link
							className='nav-link'
							to={"/register"}
						>
							Register
						</Link>
					</div>

					<div className='nav-link-container'>
						<Link
							className='nav-link'
							to={"/login"}
						>
							Login
						</Link>
					</div>
				</div>

				<Routes>
					<Route
						path='/user'
						element={<User />}
					></Route>
					<Route
						path='/register'
						element={<Register />}
					></Route>
					<Route
						path='/login'
						element={<Login />}
					></Route>
					<Route
						path='/myPublications'
						element={<UserPublications />}
					></Route>
					<Route
						path='/publication/:title'
						element={<Publication />}
					/>
					<Route
						path='/'
						element={<Home />}
					/>
				</Routes>
			</Router>
		</DataProvider>
	)
}

export { App }
