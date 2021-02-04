ARG JAVA_VERSION=11
FROM public.ecr.aws/lambda/java:${JAVA_VERSION}

LABEL author="Ben Fortuna <fortuna@micronode.com>"

COPY build/classes/java/main ${LAMBDA_TASK_ROOT}
COPY build/resources/main ${LAMBDA_TASK_ROOT}
COPY build/dependency/* ${LAMBDA_TASK_ROOT}/lib/

CMD ["org.figurate.lambda.LambdaHandler::handleRequest"]
