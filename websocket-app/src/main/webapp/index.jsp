<%-- 
    Document   : Index
    Created on : 10 oct 2023, 9:11:33
    Author     : dawmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Welcome</title>
    <link rel="stylesheet" href="styles/commoaan.css" />
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
      .no-scroll {
        overflow: hidden;
      }
    </style>
  </head>
  <body class="no-scroll">
    <div>
      <header class="text-gray-600 body-font">
        <div
          class="container mx-auto flex flex-wrap p-5 flex-col md:flex-row items-center"
        >
          <a
            class="flex title-font font-medium items-center text-gray-900 mb-4 md:mb-0 hidden md:flex"
            href="index.jsp"
          >
            <img
              class="mx-auto h-10 w-auto"
              src="resources/images/logo-bili-bili.png"
              alt="Logo Bili"
            />
          </a>
          <nav
            class="md:ml-auto md:mr-0 flex flex-wrap items-end text-base justify-center"
          >
            <a class="mr-5 hover:text-gray-900" href="index.jsp">Inicio</a>
            <a class="mr-5 hover:text-gray-900" href="/pages/app.html">Chat</a>
            <a class="mr-5 hover:text-gray-900" href="/pages/contact"
              >Contacto</a
            >
          </nav>
        </div>
      </header>

      <div class="mx-auto max-w-2xl py-32 sm:py-48 lg:py-20">
        <div class="hidden sm:mb-8 sm:flex sm:justify-center">
          <div
            class="relative rounded-full px-3 py-1 text-sm leading-6 text-gray-600 ring-1 ring-gray-900/10 hover:ring-gray-900/20"
          >
            Check out the code for this application.
            <a
              href="https://github.com/megarbon"
              class="font-semibold text-blue-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-emerald-60"
              ><span class="absolute inset-0" aria-hidden="true"></span>Open
              GitHub <span aria-hidden="true">&rarr;</span></a
            >
          </div>
        </div>
        <div class="text-center">
          <h1
            class="text-4xl font-bold tracking-tight text-gray-900 sm:text-8xl"
          >
            Bili-Bili Chat
          </h1>
          <p class="mt-6 text-lg leading-8 text-gray-600">
            Welcome to this real-time text chat page. You'll need to register as
            a user on the website and log in to use the chat.
          </p>
          <div class="mt-10 flex items-center justify-center gap-x-6">
            <a
              href="pages/Register.jsp"
              class="rounded-md bg-indigo-600 px-5 py-3 text-lg font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
              >Register</a
            >
            <a
              href="pages/Login.jsp"
              class="rounded-md bg-emerald-600 px-5 py-3 text-lg font-semibold text-white shadow-sm hover:bg-emerald-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-emerald-600"
              >Log In<span aria-hidden="true">→</span></a
            >
          </div>
        </div>
      </div>
    </div>

    <!--Fondo de pantalla-->
    <div class="px-6 pt-14 lg:px-8">
      <div
        class="inset-x-0 -top-40 -z-10 transform-gpu overflow-hidden blur-3xl sm:-top-80 absolute"
      >
        <!--Fondo superior-->
        <div
          class="fondo relative left-[calc(50%-11rem)] aspect-[1155/678] w-[36.125rem] -translate-x-1/2 rotate-[30deg] bg-gradient-to-tr from-[#ff80b5] to-[#9089fc] opacity-30 sm:left-[calc(50%-30rem)] sm:w-[72.1875rem]"
        ></div>
        <!--Fondo inferior-->
        <div
          class="fondo relative left-[calc(50%+3rem)] aspect-[1155/678] w-[36.125rem] -translate-x-1/2 bg-gradient-to-tr from-[#ff80b5] to-[#9089fc] opacity-30 sm:left-[calc(50%+36rem)] sm:w-[72.1875rem]"
        ></div>
      </div>
    </div>

    <!--Footer-->
    <footer class="text-gray-600 body-font absolute fixed bottom-0 w-full">
      <div
        class="container px-5 py-8 mx-auto flex items-center sm:flex-row flex-col"
      >
        <a
          class="flex title-font font-medium items-center md:justify-start justify-center text-gray-900"
        >
          <span class="ml-3 text-xl">Bili Bili Chat</span>
        </a>
        <p
          class="text-sm text-gray-500 sm:ml-4 sm:pl-4 sm:border-l-2 sm:border-gray-200 sm:py-2 sm:mt-0 mt-4"
        >
          © 2023 Bili-Bili —
          <a
            href="https://twitter.com/knyttneve"
            class="text-gray-600 ml-1"
            rel="noopener noreferrer"
            target="_blank"
            >@megarbon</a
          >
        </p>
        <span
          class="inline-flex sm:ml-auto sm:mt-0 mt-4 justify-center sm:justify-start"
        >
          <a class="text-gray-500">
            <svg
              fill="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              class="w-5 h-5"
              viewBox="0 0 24 24"
            >
              <path
                d="M18 2h-3a5 5 0 00-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 011-1h3z"
              ></path>
            </svg>
          </a>
          <a class="ml-3 text-gray-500">
            <svg
              fill="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              class="w-5 h-5"
              viewBox="0 0 24 24"
            >
              <path
                d="M23 3a10.9 10.9 0 01-3.14 1.53 4.48 4.48 0 00-7.86 3v1A10.66 10.66 0 013 4s-4 9 5 13a11.64 11.64 0 01-7 2c9 5 20 0 20-11.5a4.5 4.5 0 00-.08-.83A7.72 7.72 0 0023 3z"
              ></path>
            </svg>
          </a>
          <a class="ml-3 text-gray-500">
            <svg
              fill="none"
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              class="w-5 h-5"
              viewBox="0 0 24 24"
            >
              <rect width="20" height="20" x="2" y="2" rx="5" ry="5"></rect>
              <path
                d="M16 11.37A4 4 0 1112.63 8 4 4 0 0116 11.37zm1.5-4.87h.01"
              ></path>
            </svg>
          </a>
          <a class="ml-3 text-gray-500">
            <svg
              fill="currentColor"
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="0"
              class="w-5 h-5"
              viewBox="0 0 24 24"
            >
              <path
                stroke="none"
                d="M16 8a6 6 0 016 6v7h-4v-7a2 2 0 00-2-2 2 2 0 00-2 2v7h-4v-7a6 6 0 016-6zM2 9h4v12H2z"
              ></path>
              <circle cx="4" cy="4" r="2" stroke="none"></circle>
            </svg>
          </a>
        </span>
      </div>
    </footer>
  </body>
</html>
