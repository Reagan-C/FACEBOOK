<%@page contentType="text/html" import=" java.util.* " pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Facebook Clone</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <!-- Bootstrap 4.5 CSS-->
    <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
            integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
            crossorigin="anonymous"
    />

    <!-- My css -->
    <link rel="stylesheet" href="style.css"/>
</head>
<body style="background: #f0f0f0; color: #1c1e21;">
<% if (session.getAttribute("Registration Error") != null) {%>
<div class="alert alert-success" role="alert">
    <%=session.getAttribute("Registration Error").toString()%>
</div>
<%
    }
    //delete session
    session.invalidate();
%>
<section class="container main-content">
    <div class="flex-item-left">
        <div>
            <h1 style="color: #1977f2; text-align: center; margin: auto ; font-size:3.8rem; font-weight: bold; padding-top: 2rem;padding-bottom: 1.5rem ">facebook</h1>
        </div>
        <div>
            <h3 style=" text-align: center; margin-bottom: auto ; font-size: 1.2rem ">
                Facebook helps you connect and share
                with the people in your life.
            </h3>
        </div>
    </div>
    <div class="flex-item-right">
        <div class="shadow p-3 mb-5 bg-white rounded">
            <form action="LoginServlet" method="POST">
                <div class="form-group">
                    <input style="margin-bottom: 10px"
                            id="input1"
                            class="form-control form-control-lg"
                            type="text"
                            name="email"
                            placeholder="Email address or phone number"
                            required
                    />
                    <input style="margin-bottom: 15px"
                            id="input2"
                            class="form-control form-control-lg"
                            type="password"
                            name="password"
                            placeholder="Password"
                            required
                    />
                    <button style="margin-bottom: 15px"
                            id="btn1"
                            type="submit"
                            class="btn btn-primary btn-lg btn-block"
                    >
                        Log in
                    </button>
                    <p id="password" style="width: 60%; text-align: center; margin: auto">Forgotten password?</p>
                    <hr/>
                    <button style="margin-top: 40px;
                                       font-size: medium;
                                       width: 50%;
                                       margin: auto;
                                       background-color: #58b82c;
                                       color: #ffffff;"
                            onclick="modal()"
                            id="btn2"
                            type="button"
                            class="btn btn-lg btn-block btn-lg btn-block"
                    >
                        Create New Account
                    </button>
                </div>
            </form>
        </div>
        <p style="text-align: center; font-size: 14px">
            <a href="" style="text-decoration: none; color: black"
            ><strong>Create a Page</strong></a
            >
            for a celebrity, band or business.
        </p>
    </div>
</section>
<section class="container">
    <div id="modal" class="modal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title">
                        <h1>Sign Up</h1>
                        <p>It's quick and easy.</p>
                    </div>
                    <button
                            onclick="work()"
                            id="close"
                            type="button"
                            class="close"
                            data-dismiss="modal"
                            aria-label="Close"
                    >
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="SignUpServlet" method="POST">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <input
                                        type="text"
                                        name="firstname"
                                        class="form-control"
                                        id="inputEmail4"
                                        placeholder="First name"
                                        required
                                />
                            </div>
                            <div class="form-group col-md-6">
                                <input
                                        type="text"
                                        name="lastname"
                                        class="form-control"
                                        id="inputPassword4"
                                        placeholder="Lastname"
                                        required
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <input
                                    type="text"
                                    name="email"
                                    class="form-control"
                                    id="inputAddress"
                                    placeholder="Mobile number or email address"
                                    required
                            />
                        </div>
                        <div class="form-group">
                            <input
                                    type="password"
                                    name="password"
                                    class="form-control"
                                    id="inputAddress2"
                                    placeholder="New password"
                                    required
                            />
                        </div>
                        <div>
                            <small style="color: #7d7d7d">
                                By clicking Sign Up, you agree to our Terms, Data Policy and
                                Cookie Policy.
                            </small>
                            <small style="color: #7d7d7d">
                                You may receive SMS notifications from us and can opt out at
                                any time.
                            </small>
                        </div>
                        <br/>
                        <button type="submit" class="btn btn-success btn-lg btn-block">
                            Sign up
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<footer style="
                   width: 100%;
                   padding: 1rem;
                   background-color: #ffffff;
                   color: #7188b5;
     ">
    <p>
        <small><span style="color: #989898">English (UK)</span> Hausa Português (Brasil) Français (France) Español
            العربية Italiano 日本語 Deutsch
            Bahasa Indonesia हिन्दी
        </small>
    <hr style="width: 75%; margin-bottom: -1px;  margin-top: 4px; padding: 4px;"/>
    </p>
    <p>
        <small>Sign Up Log In Messenger Facebook Lite Watch People Pages Page
            categories Places Games Locations Market place Facebook Pay
        </small>
    </p>
    <p>
        <small>Groups Jobs Oculus Portal Instagram Local Fund raisers Services Voting
            Information Centre About Create ad Create Page Developers
        </small>
    </p>
    <p><small> Careers Privacy Cookies Ad Choices Terms Help Settings </small></p>
    <p class="copyright"><small>Facebook © 2022</small></p>
</footer>
</body>
<script >
//disable buttons of the first form
let input1 = document.getElementById("input1");
let input2 = document.getElementById("input2");
let btn1 = document.getElementById("btn1");
let btn2 = document.getElementById("btn2");
let btn3 = document.getElementById("password");

function work() {
    document.getElementById("modal").className = "modal";
    document.getElementsByClassName("main-content")[0].style.opacity = "1";
    document.getElementById("modal").style.display = "none";
    input1.disabled = false;
    input2.disabled = false;
    btn1.disabled = false;
    btn2.disabled = false
}

function modal() {
    input1.disabled = true;
    input2.disabled = true;
    btn1.disabled = true;
    btn2.disabled = true;

    //decrease the opacity of the first firm
    document.getElementsByClassName("main-content")[0].style.opacity = "0.2";
    document.getElementById("modal").style.opacity = "1";
    document.getElementById("modal").style.display = "";
    document.getElementById("modal").className = "";
}
</script>
</html>
