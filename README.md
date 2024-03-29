# AbnAmroDivisorAssignment

swagger: '2.0'
info:
  description: |
    **Problem Description**
    This is a function where you can provide a positive integer in the URL path. The response will contain all whole numbers from 1 to 10 by which the provided number can be divided (with no remainder).
    
    ---
    **Part 1**
    
    A user calls 'http://localhost:8080/calculator/findDivisors/20'
     - The parameter value 20 is provided to the application
     - The application calculates what divisors exist for the provided value between 1 and 10
     - The results of that calculation are returned in the response body as an integer array, e.g. [ 1, 2, 4, 5, 10 ]
    
    Calculation details
    
    So, in our example, the application is able to determine that
     - 1 x 20 = 20
     - 2 x 10 = 20
     - 4 x 5 = 20
     
    And 20 is not included in results, because it is greater than 10
    
    ---
    Part 2
    
    An optional query parameter can be added named 'evenOrOdd'. 
    In the example above - odd would produce a result of [1, 5] (i.e. the results that are odd)
    even would produce a result of [2, 4, 10] (i.e. the results that are even)
    If no value is provided, then all results are returned

  version: 0.0.1
  title: Divisibility Calculator
host: localhost:8080
basePath: /calculator
schemes:
  - http
  - https
consumes:
  - application/json
produces:
  - application/json
paths:
  '/findDivisors/{numberValue}':
    get:
      parameters:
        - in: path
          name: numberValue
          type: integer
          description: The number provided by the user
          required: true
        - in: query
          name: evenOrOdd
          type: string
          description: Whether the results should contain even or odd numbers
          required: false
      description: |
        Returns a list of numbers between 1 and 10 (inclusive) by which the value provided in the url can be divided
        Please see the top of this yaml for further info.
      operationId: getDivisorsForGivenNumber

      responses:
        '200':
          description: Successful response
          schema:
            $ref: '#/definitions/NumberArrayResult'
        '400':
          $ref: '#/responses/StandardErrorResponse'
        '401':
          $ref: '#/responses/StandardErrorResponse'
        '403':
          $ref: '#/responses/StandardErrorResponse'
        '500':
          $ref: '#/responses/StandardErrorResponse'
        '501':
          $ref: '#/responses/StandardErrorResponse'
        '502':
          $ref: '#/responses/StandardErrorResponse'
      tags:
        - divisibilityCalculatorService


parameters:
  numberValue:
    in: path
    name: numberValue
    required: true
    type: integer
    description: unique key identifying the session of the logged in user
  evenOrOdd:
    in: query
    name: evenOrOdd
    required: false
    type: string
    enum: ['even','odd']
    description: Whether the results should contain even or odd numbers from the results set


definitions:

  NumberArrayResult:
    type: object
    required:
      - numberValue
    properties:
      results:
        type: array
        example: [1,2,4,5,10]
        items:
          type: integer

  ResultCode:
    type: object
    required:
      - code
      - message
      - requestId
    properties:
      code:
        type: string
      message:
        type: string
      requestId:
        type: string


responses:

  StandardErrorResponse:
    description: |
      Indicates a bad request was made.

      ResultCode:

      Code | Message
      -----|-----------
      INVALID_REQUEST | Request format is invalid
    schema:
      $ref: '#/definitions/ResultCode'
    examples:
      application/json:
        ResultCode:
          code: STANDARD_ERROR
          message: An error has occurred


tags:
  - name: divisibilityCalculatorService
    description: Services for performing custom calculations
