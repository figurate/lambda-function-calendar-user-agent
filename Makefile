SHELL:=/bin/bash
REGISTRY?=figurate
IMAGE_NAME=lambda-function-java-template
TAGS?=latest
BUILD_ARGS?=
JAVA_VERSION=11

.PHONY: all build tag push

all: build

clean:
	docker rmi $(REGISTRY)/$(IMAGE_NAME)

build:
	 docker build -t $(REGISTRY)/$(IMAGE_NAME) ${BUILD_ARGS} --build-arg JAVA_VERSION=$(JAVA_VERSION) \
 		--build-arg HTTP_PROXY=${http_proxy} --network=host .

tag:
	echo $(TAGS) | tr "/," "-\n" | xargs -n1 -I % docker tag $(REGISTRY)/$(IMAGE_NAME) $(REGISTRY)/$(IMAGE_NAME):%

push:
	echo $(TAGS) | tr "/," "-\n" | xargs -n1 -I % docker push $(REGISTRY)/$(IMAGE_NAME):%
