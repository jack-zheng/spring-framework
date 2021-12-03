/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.core;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Juergen Hoeller
 */
class SimpleAliasRegistryTests {

	@Test
	void test_basic_function() {
		SimpleAliasRegistry registry = new SimpleAliasRegistry();
		registry.registerAlias("a", "a_1");
		System.out.println(Arrays.toString(registry.getAliases("a")));
		registry.registerAlias("a", "a_2");
		System.out.println(Arrays.toString(registry.getAliases("a")));

		registry.registerAlias("a_1", "a_a_1");
		System.out.println(Arrays.toString(registry.getAliases("a")));
		System.out.println(Arrays.toString(registry.getAliases("a_1")));

		//registry.registerAlias("a_a_1", "a");
	}

	@Test
	void test_if_key_or_value_is_empty_or_null() {
		SimpleAliasRegistry registry = new SimpleAliasRegistry();
		// 运行就直接抛异常了
		// java.lang.IllegalArgumentException: 'name' must not be empty
		//registry.registerAlias("", "testAlias");
		//registry.registerAlias("name", "");
		//registry.registerAlias(null, "value");
		//registry.registerAlias("key", null);
	}

	@Test
	void aliasChaining() {
		SimpleAliasRegistry registry = new SimpleAliasRegistry();
		registry.registerAlias("test", "testAlias");
		registry.registerAlias("testAlias", "testAlias2");
		registry.registerAlias("testAlias2", "testAlias3");

		assertThat(registry.hasAlias("test", "testAlias")).isTrue();
		assertThat(registry.hasAlias("test", "testAlias2")).isTrue();
		assertThat(registry.hasAlias("test", "testAlias3")).isTrue();
		assertThat(registry.canonicalName("testAlias")).isEqualTo("test");
		assertThat(registry.canonicalName("testAlias2")).isEqualTo("test");
		assertThat(registry.canonicalName("testAlias3")).isEqualTo("test");
	}

	@Test  // SPR-17191
	void aliasChainingWithMultipleAliases() {
		SimpleAliasRegistry registry = new SimpleAliasRegistry();
		registry.registerAlias("name", "alias_a");
		registry.registerAlias("name", "alias_b");
		assertThat(registry.hasAlias("name", "alias_a")).isTrue();
		assertThat(registry.hasAlias("name", "alias_b")).isTrue();

		registry.registerAlias("real_name", "name");
		assertThat(registry.hasAlias("real_name", "name")).isTrue();
		assertThat(registry.hasAlias("real_name", "alias_a")).isTrue();
		assertThat(registry.hasAlias("real_name", "alias_b")).isTrue();

		registry.registerAlias("name", "alias_c");
		assertThat(registry.hasAlias("real_name", "name")).isTrue();
		assertThat(registry.hasAlias("real_name", "alias_a")).isTrue();
		assertThat(registry.hasAlias("real_name", "alias_b")).isTrue();
		assertThat(registry.hasAlias("real_name", "alias_c")).isTrue();
	}

}
