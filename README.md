# RESTful API with Spring Boot

## Overview

This repository contains a API built using Java and Spring Boot. 

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)

## Features

This is a tool for students, teachers and staff 
- View courses
- Info about students, allergies and activities 
- View grades.

## Prerequisites

- Java 
- Gradle
- H2

## Getting Started

1. Clone the repository:

   ```bash
   git clone <repository-url>

## Project structure

- Controller layer
- Service layer
- Repository layer
- Database layer

## API-Endpoints

- [/api/public/login](src/main/java/com/example/demo/controller/AuthController.java)
- [/public/courses](src/main/java/com/example/demo/controller/CourseController.java)
- [/public/search/course](src/main/java/com/example/demo/controller/CourseController.java)

## Authentication

- Spring security
- JWT